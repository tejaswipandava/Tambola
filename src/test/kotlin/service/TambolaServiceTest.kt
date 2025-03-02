package service

import Helper.ClaimHelper
import exception.InvalidDataException
import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import validation.RowValidator
import kotlin.test.assertFailsWith

class TambolaServiceTest {
    private lateinit var tambolaService: TambolaService
    private lateinit var claimHelper: ClaimHelper

    @BeforeEach
    fun setUp() {
        tambolaService = TambolaService()
        claimHelper = ClaimHelper()
    }

    @Test
    fun `should throw error when unknown is sent as a claim`() {
        val expectedError = "invalid claim"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5)
        val claim = "tejaswi"

        val exception = assertFailsWith<InvalidDataException> {
            tambolaService.claim(ticket, announcedNumber, claim)
        }

        assertEquals(expectedError, exception.message)
    }

    @Test
    fun `should return true when top row claim is valid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5)
        val claim = "Top Row"
        mockkConstructor(RowValidator::class)
        every { anyConstructed<RowValidator>().validate(ticket, announcedNumber, claim) } returns true

        val response = tambolaService.claim(ticket, announcedNumber, claim)

        verify(exactly = 1) { anyConstructed<RowValidator>().validate(ticket, announcedNumber, claim) }
        assertTrue(response)
    }

    @Test
    fun `should return false when top row claim is invalid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5)
        val claim = "Bottom Row"

        val response = tambolaService.claim(ticket, announcedNumber, claim)

        assertFalse(response)
    }

    @Test
    fun `should return true when full house claim is valid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        val claim = "Full House"

        val response = tambolaService.claim(ticket, announcedNumber, claim)

        assertTrue(response)
    }

    @Test
    fun `should return false when full house claim is invalid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5)
        val claim = "Full House"


        val response = tambolaService.claim(ticket, announcedNumber, claim)

        assertFalse(response)
    }

    @Test
    fun `should return true when early five claim is valid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 5, 9, 10, 15)
        val claim = "early five"

        val response = tambolaService.claim(ticket, announcedNumber, claim)

        assertTrue(response)
    }

    @Test
    fun `should return false when early five claim is invalid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4)
        val claim = "early five"


        val response = tambolaService.claim(ticket, announcedNumber, claim)

        assertFalse(response)
    }
}