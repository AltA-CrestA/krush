package pl.touk.krush.one2one

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
data class Employee(
    @Id @GeneratedValue
    val id: Long? = null,

    @OneToOne(mappedBy = "employee")
    val employeeInfo: EmployeeInfo? = null
)

@Entity
data class EmployeeInfo(

    @Id
    val login: String,

    @OneToOne
    @JoinColumn(name = "employee_id")
    val employee: Employee? = null,

    @OneToOne(mappedBy = "employeeInfo")
    val parkingSpot: ParkingSpot? = null
)

@Entity
data class ParkingSpot(

    @Id @GeneratedValue
    val id: Long? = null,

    val code: String,

    @OneToOne
    @JoinColumn(name = "employee_info_id")
    val employeeInfo: EmployeeInfo?
)
