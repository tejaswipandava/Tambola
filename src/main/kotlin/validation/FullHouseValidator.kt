package validation

import exception.InvalidDataException
import model.Ticket

class FullHouseValidator : ClaimValidator {
    override fun validate(ticket: Ticket, announcedNumber: List<Int>, claim: String): Boolean {
        if (claim.lowercase() != "full house") throw InvalidDataException("invalid claim")

        var count = 0

        announcedNumber.forEachIndexed { index, value ->
            if (ticket.row.flatten().contains(value)) count++
            if (count == 15) return index == announcedNumber.lastIndex
        }

        return false
    }
}