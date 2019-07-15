fun main() {

    val strava = Service(
        "Strava",
        setOf("SRT", "CVT", "Perkiomen")
    ) { userId, route -> "$userId$route"}

    val rwgps = Service(
        "RWGPS",
        setOf("CVT", "Perkiomen", "Welsh Mountain")
    ) { userId, route -> "$route$userId"}

    val komoot = Service(
        "Komoot",
        setOf("SRT", "Welsh Mountain", "Oaks to Philly")
    ) { userId, route -> "$userId$route$userId"}

    val serviceManager = ServiceManager()
    serviceManager.addService(strava)
    serviceManager.addService(rwgps)
    serviceManager.addService(komoot)

    val randomUserId = (0..1000).random()

    // Services can retrieve all routes
    println("Get all routes for Strava: ${strava.routes}")
    println("Get all routes for RWGPS: ${rwgps.routes}")
    println("Get all routes for Komoot: ${komoot.routes}")

    // Services can retrieve routes for a specific user
    println("Get all Strava routes for user $randomUserId: ${strava.getRoutesByUserId(randomUserId)}")
    println("Get all RWGPS routes for user $randomUserId: ${rwgps.getRoutesByUserId(randomUserId)}")
    println("Get all Komoot routes for user $randomUserId: ${komoot.getRoutesByUserId(randomUserId)}")

    // Return the list of the routes across all services.
    println("All routes: ${serviceManager.getAllRoutes()}")

    // Return a unique list of the routes across all services.
    println("All Unique Routes: ${serviceManager.getAllUniqueRoutes()}")

    // Given a user id and return a list of all the routes the user has across all services.
    println("All routes across all services for user $randomUserId: " +
            "${serviceManager.getAllRoutesByUserId(randomUserId)}")

    // Given a user id and a subset of the list of services (e.g. ["Strava", "RWGPS"])
    // return the user's routes for only the services listed.
    println("All routes for user $randomUserId in Strava and RWGPS: " +
            "${serviceManager.getAllRoutesByUserIdForServices(randomUserId, setOf("strava", "rwgps"))}")

}