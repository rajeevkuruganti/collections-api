package com.circlesllc.collections.api.service

import com.circlesllc.collections.api.dataobject.CollectionGroupDO
import com.circlesllc.collections.api.entities.CollectionGroup
import com.circlesllc.collections.api.repository.CollectionGroupRepo
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class CollectionGroupServiceTest {

    private lateinit var collectionGroupRepo: CollectionGroupRepo
    private lateinit var collectionGroupService: CollectionGroupService

    @BeforeEach
    fun setUp() {
        collectionGroupRepo = mockk()
        collectionGroupService = CollectionGroupService()
        collectionGroupService.collectionGroupRepo = collectionGroupRepo
    }

    @Test
    fun `saveNewItem should save and return collection group`() {
        val dto = CollectionGroupDO("Test Group", "{}")
        val expectedEntity = CollectionGroup(1L, "Test Group", "{}")
        
        every { collectionGroupRepo.save(any()) } returns expectedEntity

        val result = collectionGroupService.saveNewItem(dto)

        assertNotNull(result)
        assertEquals(1L, result.id)
        assertEquals("Test Group", result.name)
        verify(exactly = 1) { collectionGroupRepo.save(any()) }
    }

    @Test
    fun `deleteById should return true when successful`() {
        val id = 1L
        every { collectionGroupRepo.deleteById(id) } returns Unit

        val result = collectionGroupService.deleteById(id)

        assertTrue(result)
        verify(exactly = 1) { collectionGroupRepo.deleteById(id) }
    }

    @Test
    fun `deleteById should return false when exception occurs`() {
        val id = 1L
        every { collectionGroupRepo.deleteById(id) } throws RuntimeException("Error")

        val result = collectionGroupService.deleteById(id)

        assertFalse(result)
        verify(exactly = 1) { collectionGroupRepo.deleteById(id) }
    }

    @Test
    fun `findByName should return collection group when found`() {
        val name = "Test Group"
        val expectedEntity = CollectionGroup(1L, name, "{}")
        every { collectionGroupRepo.findByName(name) } returns Optional.of(expectedEntity)

        val result = collectionGroupService.findByName(name)

        assertTrue(result.isPresent)
        assertEquals(name, result.get().name)
        verify(exactly = 1) { collectionGroupRepo.findByName(name) }
    }

    @Test
    fun `findByName should return empty when not found or exception`() {
        val name = "Unknown"
        every { collectionGroupRepo.findByName(name) } returns Optional.empty()

        var result = collectionGroupService.findByName(name)
        assertFalse(result.isPresent)

        every { collectionGroupRepo.findByName(name) } throws RuntimeException("Error")
        result = collectionGroupService.findByName(name)
        assertFalse(result.isPresent)
    }

    @Test
    fun `findAll should return all collection groups`() {
        val list = listOf(CollectionGroup(1L, "G1", "{}"), CollectionGroup(2L, "G2", "{}"))
        every { collectionGroupRepo.findAll() } returns list

        val result = collectionGroupService.findAll()

        assertEquals(2, result.count())
        verify(exactly = 1) { collectionGroupRepo.findAll() }
    }

    @Test
    fun `findById should return collection group when found`() {
        val id = 1L
        val expectedEntity = CollectionGroup(id, "Test Group", "{}")
        every { collectionGroupRepo.findById(id) } returns Optional.of(expectedEntity)

        val result = collectionGroupService.findById(id)

        assertTrue(result.isPresent)
        assertEquals(id, result.get().id)
        verify(exactly = 1) { collectionGroupRepo.findById(id) }
    }

    @Test
    fun `findById should return empty when not found or exception`() {
        val id = 1L
        every { collectionGroupRepo.findById(id) } returns Optional.empty()

        var result = collectionGroupService.findById(id)
        assertFalse(result.isPresent)

        every { collectionGroupRepo.findById(id) } throws RuntimeException("Error")
        result = collectionGroupService.findById(id)
        assertFalse(result.isPresent)
    }

}