package com.galdino.rgvpacientes.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.util.ReflectionTestUtils;

import com.galdino.rgvpacientes.dto.mapper.MaterialMapper;
import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import com.galdino.rgvpacientes.dto.material.MaterialInput;
import com.galdino.rgvpacientes.model.Material;
import com.galdino.rgvpacientes.repository.MaterialRepository;

import liquibase.pro.packaged.ma;

public class MaterialServiceTest {

    private MaterialService materialService;
    private MaterialMapper materialMapper;
    private MovementItemService movementItemService;
    private MaterialRepository materialRepository;
    private EntityManager manager;

    @BeforeEach
    public void setUp() {
        materialRepository = mock(MaterialRepository.class);
        materialMapper = mock(MaterialMapper.class);
        movementItemService = mock(MovementItemService.class);
        manager = mock(EntityManager.class);
        materialService = new MaterialService(materialRepository, materialMapper, movementItemService);
        ReflectionTestUtils.setField(materialService, "manager", manager);
    }

    @Test
    void testExistsById() {
        when(materialRepository.existsById(1L)).thenReturn(true);
        boolean existsById = materialService.existsById(1L);
        assert (existsById);
    }

    @Test
    void testCreate() {
        MaterialInput materialInput = new MaterialInput();
        materialInput.setName("Material");
        when(materialRepository.existsByName("Material")).thenReturn(false);

        Material material = new Material();
        material.setName("Material");
        when(materialMapper.toEntity(materialInput)).thenReturn(material);

        when(materialRepository.save(any(Material.class))).thenAnswer(invocation -> {
            Material materialSave = invocation.getArgument(0);
            materialSave.setId(1L);
            materialSave.setName("Material");
            return materialSave;
        });

        when(materialMapper.toDTO(material)).thenReturn(MaterialDTO.builder().id(1L).name("Material").build());
        MaterialDTO materialDTO = materialService.create(materialInput);
        assert (materialDTO.getId() == 1L);
    }

}
