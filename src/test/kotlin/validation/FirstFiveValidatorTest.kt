package validation

import Helper.ClaimHelper
import exception.InvalidDataException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class FirstFiveValidatorTest {

    private lateinit var firstFiveValidator: FirstFiveValidator
    private lateinit var claimHelper: ClaimHelper

    @BeforeEach
    fun setup() {
        firstFiveValidator = FirstFiveValidator()
        claimHelper = ClaimHelper()
    }

    @Test
    fun `should throw error when claim is not full house`() {
        val expectedError = "invalid claim"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5)
        val claim = "EarlyFive"

        val exception = assertFailsWith<InvalidDataException> {
            firstFiveValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)
    }

    @Test
    fun `should return true when claim is valid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 3, 7, 12, 15)
        val claim = "Early Five"

        val response = firstFiveValidator.validate(ticket, announcedNumber, claim)
        assertTrue(response)
    }

    @Test
    fun `should return false when claim is invalid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5, 6)
        val claim = "Early Five"

        val response = firstFiveValidator.validate(ticket, announcedNumber, claim)
        assertFalse(response)
    }
}