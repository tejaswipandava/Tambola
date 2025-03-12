package validation

import exception.InvalidDataException
import model.Ticket

class RowValidator : ClaimValidator {
//    override fun validate(ticket: Ticket, announcedNumber: Set<Int>, claim: String): Boolean {
//        var count = 0
//        val row: Set<Int> = when (claim.lowercase()) {
//            "top row" -> ticket.row[0]
//            "middle row" -> ticket.row[1]
//            "bottom row" -> ticket.row[2]
//            else -> throw InvalidDataException("invalid claim")
//        }
//
//        announcedNumber.forEachIndexed { index, value ->
//            if (row.contains(value)) count++
//            if (count == 5) {
//                return index == announcedNumber.size - 1
//            }
//        }
//
//        return false
//    }

//    fun validate2(ticket: Ticket, announcedNumber: Set<Int>, rowIndex: Int): Boolean {
//
//        val row: Set<Int> = ticket.row[rowIndex]
//
//        row.forEach { value->
//            if(!announcedNumber.contains(value)) return false
//        }
//
//        return row.contains(announcedNumber.last())
//    }


    //row.all { it in announcedNumber } && row.contains(announcedNumber.last())
    override fun validate(ticket: Ticket, announcedNumber: Set<Int>, claim: String): Boolean {
        val row: Set<Int> = when (claim.lowercase()) {
            "top row" -> ticket.row[0]
            "middle row" -> ticket.row[1]
            else -> ticket.row[2]
        }
//        val row: Set<Int> = ticket.row[rowIndex]

        return checkxx(row, announcedNumber, claim)
    }

    private fun checkxx(
        row: Set<Int>,
        announcedNumber: Set<Int>,
        claim: String
    ): Boolean {
        if (!row.all { it in announcedNumber }) {
            println("Invalid Row Claim: ${claim} doesnt contains the announcedNumber")
            return false
        }
        //print / log the message and return false

        if (!row.contains(announcedNumber.last())) {
            println("Invalid Row Claim: last announcedNumber is not part of the ${claim} claim")
            return false
        }

        return true
    }
}

/*
    req i should know, eazy way to know why claim was rejected.
    reduce return statements
    and way to know what validation has failed
 */