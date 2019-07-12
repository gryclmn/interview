// Are there times you would want multiple ServiceManagers?
// If not, this class probably should be a singleton.
class ServiceManager {

    val services = mutableSetOf<Service>()

    fun getAllRoutes(): List<String> {
        val allRoutes = mutableListOf<String>()

        for (service in services) {
            allRoutes.addAll(service.routes)
        }

        return allRoutes
    }

    fun getAllUniqueRoutes() = getAllRoutes().distinct()

    fun getAllRoutesByUserId(userId: Int) = getAllRoutesByUserIdForServices(userId, services)

    fun getAllRoutesByUserIdForServices(userId: Int, selectServices: Set<Service>): List<String> {
        val allRoutesByUserIdForServices = mutableListOf<String>()

        for (service in selectServices) {
            allRoutesByUserIdForServices.addAll(service.getRoutesByUserId(userId))
        }

        return allRoutesByUserIdForServices
    }
}