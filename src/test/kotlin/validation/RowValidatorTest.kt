package validation

import Helper.ClaimHelper
import exception.InvalidDataException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class RowValidatorTest {

    private lateinit var rowValidator: RowValidator
    private lateinit var claimHelper: ClaimHelper

    @BeforeEach
    fun setup() {
        rowValidator = RowValidator()
        claimHelper = ClaimHelper()
    }

//    @Test
//    fun `should throw an error when claim is invalid`() {
//        val expectedError = "invalid claim"
//        val ticket = claimHelper.generateTicket()
//        val announcedNumber = setOf(1, 2, 3, 4, 5)
//        val claim = "full House"
//
//        val exception = assertFailsWith<InvalidDataException> {
//            rowValidator.validate(ticket, announcedNumber, claim)
//        }
//        assertEquals(expectedError, exception.message)
//    }

    @Test
    fun `should return true when top row claim is valid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5)
        val claim = "Top Row"

        val response = rowValidator.validate(ticket, announcedNumber, claim)

        assertTrue(response)
    }

    @Test
    fun `should return true when middle row claim is valid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(6, 7, 8, 9, 10)
        val claim = "middle Row"

        val response = rowValidator.validate(ticket, announcedNumber, claim)

        assertTrue(response)
    }

    @Test
    fun `should return true when bottom row claim is valid`() {
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(11, 12, 13, 14, 15)
        val claim = "bottom Row"

        val response = rowValidator.validate(ticket, announcedNumber, claim)

        assertTrue(response)
    }

    @Test
    fun `should throw error when last announedNumber is not part of top row`() {
        val expectedError = "Invalid Row Claim: last announcedNumber is not part of the Top Row claim"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 4, 5, 6)
        val claim = "Top Row"

        val exception = assertFailsWith<InvalidDataException> {
            rowValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)

    }

    @Test
    fun `should throw error when top row doesnt contains the announcedNumber`() {
        val expectedError = "Invalid Row Claim: Top Row doesnt contains the announcedNumber"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(1, 2, 3, 5, 6)
        val claim = "Top Row"

        val exception = assertFailsWith<InvalidDataException> {
            rowValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)

    }

    @Test
    fun `should throw error when last announedNumber is not part of middle row`() {
        val expectedError = "Invalid Row Claim: last announcedNumber is not part of the Middle Row claim"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(6, 7, 8, 9, 10, 11)
        val claim = "Middle Row"

        val exception = assertFailsWith<InvalidDataException> {
            rowValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)

    }

    @Test
    fun `should throw error when middle row doesnt contains the announcedNumber`() {
        val expectedError = "Invalid Row Claim: Middle Row doesnt contains the announcedNumber"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(4, 7, 8, 9, 10)
        val claim = "Middle Row"

        val exception = assertFailsWith<InvalidDataException> {
            rowValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)

    }

    @Test
    fun `should throw error when last announedNumber is not part of bottom row`() {
        val expectedError = "Invalid Row Claim: last announcedNumber is not part of the Bottom Row claim"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(11, 12, 13, 14, 15, 16)
        val claim = "Bottom Row"

        val exception = assertFailsWith<InvalidDataException> {
            rowValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)

    }

    @Test
    fun `should throw error when bottom row doesnt contains the announcedNumber`() {
        val expectedError = "Invalid Row Claim: Bottom Row doesnt contains the announcedNumber"
        val ticket = claimHelper.generateTicket()
        val announcedNumber = setOf(7, 12, 13, 14, 15)
        val claim = "Bottom Row"

        val exception = assertFailsWith<InvalidDataException> {
            rowValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)

    }
}