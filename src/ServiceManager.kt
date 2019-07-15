// Are there times you would want multiple ServiceManagers?
// If not, this class probably should be a singleton.
class ServiceManager {

    val services = mutableMapOf<String, Service>()

    fun addService(newService: Service) {
        services[newService.name.toUpperCase()] = newService
    }

    fun getAllRoutes(): List<String> {
        val allRoutes = mutableListOf<String>()

        for (service in services) {
            allRoutes.addAll(service.value.routes)
        }

        return allRoutes
    }

    fun getAllUniqueRoutes() = getAllRoutes().distinct()

    fun getAllRoutesByUserId(userId: Int): List<String> {
        val allRoutesByUserId = mutableListOf<String>()

        for (service in services) {
            allRoutesByUserId.addAll(service.value.getRoutesByUserId(userId))
        }

        return allRoutesByUserId
    }

    fun getAllRoutesByUserIdForServices(userId: Int, selectServices: Set<String>): List<String> {
        val allRoutesByUserIdForServices = mutableListOf<String>()

        for (serviceString in selectServices) {
            val currentKey = serviceString.toUpperCase()

            // If a currently unknown service is requested here then I imagine we should
            // check for that service somehow, such as a network call?
            // For the sake of this exercise I'm keeping it simple and only instantiating
            // a new service with no routes or modifiers.
            if (services.containsKey(currentKey)) {
                val currentService = services[currentKey] ?: Service(serviceString, setOf())
                allRoutesByUserIdForServices.addAll(currentService.getRoutesByUserId(userId))
            } else {
                // Handle error of unknown service. For now simply print to console.
                println("The $serviceString service is not recognized.")
            }
        }

        return allRoutesByUserIdForServices
    }
}