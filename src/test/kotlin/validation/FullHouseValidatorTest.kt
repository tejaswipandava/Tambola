package validation

import Helper.ClaimHelper
import exception.InvalidDataException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class FullHouseValidatorTest {

    private lateinit var fullHouseValidator: FullHouseValidator
    private lateinit var claimHelper: ClaimHelper

    @BeforeEach
    fun setup() {
        fullHouseValidator = FullHouseValidator()
        claimHelper = ClaimHelper()
    }

    @Test
    fun `should throw error when claim is not full house`() {
        val expectedError = "invalid claim"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5)
        val claim = "full-House"

        val exception = assertFailsWith<InvalidDataException> {
            fullHouseValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)
    }

    @Test
    fun `should return true when claim is valid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        val claim = "Full House"

        val response = fullHouseValidator.validate(ticket, announcedNumber, claim)
        assertTrue(response)
    }

    @Test
    fun `should return false when claim is invalid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val claim = "Full House"

        val response = fullHouseValidator.validate(ticket, announcedNumber, claim)
        assertFalse(response)
    }
    @Test
    fun `should return false when claim is invalid due to incorrect announcedNumber`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12, 14, 15, 16)
        val claim = "Full House"

        val response = fullHouseValidator.validate(ticket, announcedNumber, claim)
        assertFalse(response)
    }
}