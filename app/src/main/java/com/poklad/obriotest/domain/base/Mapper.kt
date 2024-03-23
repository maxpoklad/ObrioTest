package com.poklad.obriotest.domain.base

interface Mapper<Source, Destination> {
    fun map(data: Source): Destination
}
