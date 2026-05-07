package com.elearning.service;

import com.elearning.dao.BaiHocDAO;
import com.elearning.model.BaiHoc;
import com.elearning.service.impl.BaiHocServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BaiHocServiceTest {

    @Mock
    private BaiHocDAO baiHocDAO;

    @InjectMocks
    private BaiHocServiceImpl baiHocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBaiHoc_ThrowException_WhenNameIsEmpty() {
        BaiHoc invalidBaiHoc = new BaiHoc();
        invalidBaiHoc.setTenBH(""); 

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            baiHocService.addBaiHoc(invalidBaiHoc);
        });

        assertEquals("Tên bài học không được để trống!", exception.getMessage());
        verify(baiHocDAO, never()).insert(any(BaiHoc.class));
    }

    @Test
    public void testAddBaiHoc_Success_WhenDataValid() {
        BaiHoc validBaiHoc = new BaiHoc();
        validBaiHoc.setTenBH("Color Theory trong UI");
        validBaiHoc.setMaDM(1);

        when(baiHocDAO.insert(any(BaiHoc.class))).thenReturn(true);

        boolean result = baiHocService.addBaiHoc(validBaiHoc);

        assertTrue(result);
        verify(baiHocDAO, times(1)).insert(validBaiHoc);
    }
}
