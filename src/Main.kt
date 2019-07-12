fun main() {

    val strava = Service(
        setOf("SRT", "CVT", "Perkiomen"),
        isPrependedWithUserId = true
    )
    val rwgps = Service(
        setOf("CVT", "Perkiomen", "Welsh Mountain"),
        isAppendedWithUserId = true
    )
    val komoot = Service(
        setOf("SRT", "Welsh Mountain", "Oaks to Philly"),
        isPrependedWithUserId = true,
        isAppendedWithUserId = true
    )
    val serviceManager = ServiceManager()
    serviceManager.services.add(strava)
    serviceManager.services.add(rwgps)
    serviceManager.services.add(komoot)

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

    // Given a user id and a subset of the list of services (e.g. ["Strava", "Komoot"])
    // return the user's routes for only the services listed.
    println("All routes for user $randomUserId in Strava and RWGPS: " +
            "${serviceManager.getAllRoutesByUserIdForServices(randomUserId, setOf(strava, komoot))}")

}