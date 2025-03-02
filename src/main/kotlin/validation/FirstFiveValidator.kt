package validation

import exception.InvalidDataException
import model.Ticket

class FirstFiveValidator : ClaimValidator {
    override fun validate(ticket: Ticket, announcedNumber: Set<Int>, claim: String): Boolean {
        if (claim.lowercase() != "early five") throw InvalidDataException("invalid claim")

        var count = 0

        announcedNumber.forEachIndexed { index, value ->
            if (ticket.row.flatten().contains(value)) count++
            if (count == 5) return index == announcedNumber.size - 1
        }

        return false
    }
}