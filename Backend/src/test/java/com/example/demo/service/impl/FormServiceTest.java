package com.example.demo.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.entity.Form;
import com.example.demo.repository.FormRepository;
import com.example.demo.service.FormService;

@SpringBootTest
public class FormServiceTest {
    @Autowired
    private FormService formService;

    @MockBean
    private FormRepository formRepository;

    @Test
    public void createFormTest() {
        formService.createForm(1);
        verify(formRepository).save(argThat(new ArgumentMatcher<Form>() {
            @Override
            public boolean matches(Form argument) {
                return  argument.getDurationInDays() == 1 &&
                        argument.isOpen() &&
                        argument.getOpeningTime().isBefore(LocalDateTime.now());
            }
        }));
    }

    @Test
    public void dontCloseFormThatMustStayOpenTest() {
        Form test = new Form();
        test.setOpen(true);
        test.setOpeningTime(LocalDateTime.now().minusDays(2));
        test.setDurationInDays(40); // really long form, wont close today
        
        ArrayList<Form> testList = new ArrayList<>();
        testList.add(test);

        when(formRepository.findOpenFormsBeforeTime(any())).thenReturn(testList);
        
        formService.closeForm();
        verify(formRepository).findOpenFormsBeforeTime(argThat(new ArgumentMatcher<LocalDateTime>() {
            @Override
            public boolean matches(LocalDateTime argument) {
                return argument.isBefore(LocalDateTime.now().minusDays(1));
            }
        }));
        verify(formRepository, times(0)).save(any());
        // we souldn't save the form as it is still open
    }
    
    @Test
    public void closeFormThatMustEndTest() {
        Form test = new Form();
        test.setOpen(true);
        test.setOpeningTime(LocalDateTime.now().minusDays(5));
        test.setDurationInDays(1); // really short form, should be closed for a long time

        ArrayList<Form> testList = new ArrayList<>();
        testList.add(test);

        when(formRepository.findOpenFormsBeforeTime(any())).thenReturn(testList);
        
        formService.closeForm();
        verify(formRepository).findOpenFormsBeforeTime(argThat(new ArgumentMatcher<LocalDateTime>() {
            @Override
            public boolean matches(LocalDateTime argument) {
                return argument.isBefore(LocalDateTime.now().minusDays(1));
            }
        }));
        verify(formRepository).save(argThat(new ArgumentMatcher<Form>() {
            @Override
            public boolean matches(Form argument) {
                return !argument.isOpen();   // the form should still be open as it lasts 40 days
            }
        }));
    }

    @Test
    public void closeFormManualyTest() {
        Form test = new Form();
        test.setOpen(true);
        test.setOpeningTime(LocalDateTime.now().minusDays(5));
        test.setDurationInDays(1); // really short form, should be closed for a long time

        ArrayList<Form> testList = new ArrayList<>();
        testList.add(test);

        when(formRepository.findAll()).thenReturn(testList);
        
        formService.closeFormManualy();
        verify(formRepository).findAll();
        verify(formRepository).save(argThat(new ArgumentMatcher<Form>() {
            @Override
            public boolean matches(Form argument) {
                return !argument.isOpen();
            }
        }));
    }

    @Test
    public void isFormOpenTest() {
        Form test = new Form();
        test.setOpen(true);

        ArrayList<Form> testList = new ArrayList<>();
        testList.add(test);

        when(formRepository.findAll()).thenReturn(testList);
        
        assert formService.isFormOpen();
        verify(formRepository).findAll();
    }
}
