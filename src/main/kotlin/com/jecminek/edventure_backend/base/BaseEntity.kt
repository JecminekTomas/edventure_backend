package com.jecminek.edventure_backend.base

import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @SequenceGenerator(name="seq", initialValue=10001, allocationSize=100)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    val id: Long = 0
}