package validation

import model.Ticket

interface ClaimValidator {
    fun validate(ticket: Ticket, announcedNumber: Set<Int>, claim: String): Boolean
}