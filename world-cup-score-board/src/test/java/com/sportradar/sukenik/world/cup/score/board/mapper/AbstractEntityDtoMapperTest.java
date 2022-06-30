package com.sportradar.sukenik.world.cup.score.board.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AbstractEntityDtoMapperTest {

    @Spy
    private NullObjectEntityDtoMapper mapper;

    @SuppressWarnings("ConstantConditions")
    @Test
    void mapAllDtosToEntitiesNullTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> mapper.mapAllDtosToEntities(null));
    }

    @Test
    void mapAllDtosToEntitiesEmptyListTest() {

        List<Object> entitiesList = mapper.mapAllDtosToEntities(new ArrayList<>());

        Assertions.assertNotNull(entitiesList);
        Assertions.assertTrue(entitiesList.isEmpty());
    }

    @Test
    void mapAllDtosToEntitiesAllNullElementsTest() {

        List<Object> nullElementsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nullElementsList.add(null);
        }

        List<Object> entitiesList = mapper.mapAllDtosToEntities(nullElementsList);

        Assertions.assertNotNull(entitiesList);
        Assertions.assertTrue(entitiesList.isEmpty());
    }

    @Test
    void mapAllDtosToEntitiesSomeNullElementsTest() {

        List<Object> nullElementsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                nullElementsList.add(null);
            } else {
                nullElementsList.add(new Object());
            }
        }
        nullElementsList.add(new Object());

        doReturn(new Object()).when(mapper).mapDtoToEntity(any());

        List<Object> entitiesList = mapper.mapAllDtosToEntities(nullElementsList);

        Assertions.assertNotNull(entitiesList);
        Assertions.assertEquals(6, entitiesList.size());
    }

    @Test
    void mapAllDtosToEntitiesCorrectSizeTest() {

        List<Object> listOfDtos = List.of(new Object(), new Object(), new Object());

        doReturn(new Object()).when(mapper).mapDtoToEntity(any());

        List<Object> listOfEntities = mapper.mapAllDtosToEntities(listOfDtos);

        Assertions.assertEquals(3, listOfEntities.size());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void mapAllEntitiesToDtosNullTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> mapper.mapAllEntitiesToDtos(null));
    }

    @Test
    void mapAllEntitiesToDtosEmptyListTest() {

        List<Object> entitiesList = mapper.mapAllEntitiesToDtos(new ArrayList<>());

        Assertions.assertNotNull(entitiesList);
        Assertions.assertTrue(entitiesList.isEmpty());
    }

    @Test
    void mapAllEntitiesToDtosAllNullElementsTest() {

        List<Object> nullElementsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nullElementsList.add(null);
        }

        List<Object> entitiesList = mapper.mapAllEntitiesToDtos(nullElementsList);

        Assertions.assertNotNull(entitiesList);
        Assertions.assertTrue(entitiesList.isEmpty());
    }

    @Test
    void mapAllEntitiesToDtosSomeNullElementsTest() {

        List<Object> nullElementsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                nullElementsList.add(null);
            } else {
                nullElementsList.add(new Object());
            }
        }
        nullElementsList.add(new Object());

        doReturn(new Object()).when(mapper).mapEntityToDto(any());

        List<Object> entitiesList = mapper.mapAllEntitiesToDtos(nullElementsList);

        Assertions.assertNotNull(entitiesList);
        Assertions.assertEquals(6, entitiesList.size());
    }

    @Test
    void mapAllEntitiesToDtosCorrectSizeTest() {

        List<Object> listOfDtos = List.of(new Object(), new Object(), new Object());

        doReturn(new Object()).when(mapper).mapEntityToDto(any());

        List<Object> listOfEntities = mapper.mapAllEntitiesToDtos(listOfDtos);

        Assertions.assertEquals(3, listOfEntities.size());
    }

    private static class NullObjectEntityDtoMapper extends AbstractEntityDtoMapper<Object, Object> {

        @Override
        public Object mapEntityToDto(Object entity) {

            return null;
        }

        @Override
        public Object mapDtoToEntity(Object dto) {

            return null;
        }
    }
}