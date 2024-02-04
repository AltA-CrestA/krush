package pl.touk.krush.one2many.uni

import java.util.UUID
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.Transient

@Entity
@Table(name = "one2many_uni_customers")
data class Customer(
    @Id @GeneratedValue
    @Column(name = "customerId")
    val id: Long? = null,

    @Column(name = "name", length = 100)
    val name: String,

    val age: Long?,

    @OneToMany
    @JoinColumn(name = "customer_id")
    val phones: List<Phone> = emptyList(),

    @OneToMany
    @JoinColumn(name = "customer_id")
    val addresses: List<Address> = emptyList(),

    @Transient
    val currentAddress: Address? = null
)

@Entity
@Table(name = "one2many_uni_phones")
data class Phone(
    @Id @GeneratedValue
    val id: Int? = null,

    @Column(name = "number")
    val number: String
)

@Entity
@Table(name = "one2many_uni_adresses")
data class Address(
    @Id
    val id: UUID,

    @Column(name = "city")
    val city: String,

    @Column(name = "street")
    val street: String,

    @Column(name = "house")
    val houseNo: String,

    @Column(name = "apartment")
    val apartmentNo: String
)
