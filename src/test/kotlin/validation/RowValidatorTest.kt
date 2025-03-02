package validation

import exception.InvalidDataException
import model.Ticket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class RowValidatorTest {

    private lateinit var rowValidator: RowValidator

    @BeforeEach
    fun setup() {
        rowValidator = RowValidator()
    }

    @Test
    fun `should throw an error when claim is invalid`() {
        val expectedError = "invalid claim"
        val ticket = generateTicket()
        val announcedNumber = listOf(1, 2, 3, 4, 5)
        val claim = "full House"

        val exception = assertFailsWith<InvalidDataException> {
            rowValidator.validate(ticket, announcedNumber, claim)
        }
        assertEquals(expectedError, exception.message)
    }

    @Test
    fun `should return true when top row claim is valid`() {
        val ticket = generateTicket()
        val announcedNumber = listOf(1, 2, 3, 4, 5)
        val claim = "Top Row"

        val response = rowValidator.validate(ticket, announcedNumber, claim)

        assertTrue(response)
    }

    @Test
    fun `should return true when middle row claim is valid`() {
        val ticket = generateTicket()
        val announcedNumber = listOf(6, 7, 8, 9, 10)
        val claim = "middle Row"

        val response = rowValidator.validate(ticket, announcedNumber, claim)

        assertTrue(response)
    }

    @Test
    fun `should return false when top row claim is invalid`() {
        val ticket = generateTicket()
        val announcedNumber = listOf(1, 2, 3, 4, 5, 6)
        val claim = "Top Row"

        val response = rowValidator.validate(ticket, announcedNumber, claim)

        assertFalse(response)
    }

    fun generateTicket(): Ticket {
        return Ticket(listOf(setOf(1, 2, 3, 4, 5), setOf(6, 7, 8, 9, 10), setOf(11, 12, 13, 14, 15)))
    }
}