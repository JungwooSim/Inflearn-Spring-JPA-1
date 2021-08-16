package me.jpabook.jpashop.domain

import javax.persistence.Embeddable

@Embeddable
class Address(city: String, street: String, zipcode: String) {
    var city: String = city

    var street: String = street

    var zipcode: String = zipcode
}
