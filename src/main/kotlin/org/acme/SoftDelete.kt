package org.acme

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.hibernate.annotations.SoftDelete

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
class SoftDeleteController {
    @Inject
    lateinit var repository: CustomerRepository

    @GET
    fun hello() {
        repository.findAll()
    }
}

@Entity
@SoftDelete
class Customer {
    @Id
    var id: Long? = null
    var name: String? = null
}

@Entity
class Order {
    @Id
    var id: Long? = null
    @ManyToOne(fetch = FetchType.LAZY)
    var customer: Customer? = null
}

@ApplicationScoped
class CustomerRepository : PanacheRepository<Customer>