package com.coffeeculture.sources.transformers

interface Transformer<I, O> {
    fun transform(source: I): O
    fun transform(source: Iterable<I>): Iterable<O>
}
