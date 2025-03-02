package validation

import model.Ticket

interface ClaimValidator {
    fun validate(ticket: Ticket, announcedNumber: List<Int>, claim: String): Boolean
}