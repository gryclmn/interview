class Service(
    // Instructions state the routes are fixed for this exercise,
    // therefore we are sticking with an immutable collection for routes.
    // I imagine in the Karoo the routes would be mutable.
    val routes: Set<String>,
    val isPrependedWithUserId: Boolean = false,
    val isAppendedWithUserId: Boolean = false,
    val isAllUpperCase: Boolean = false) {

    private fun modifyRouteWithUserId(route: String, userId: Int): String {
        var modifiedRoute = route
        if (isPrependedWithUserId) modifiedRoute = "$userId$modifiedRoute"
        if (isAppendedWithUserId) modifiedRoute = "$modifiedRoute$userId"
        if (isAllUpperCase) modifiedRoute = modifiedRoute.toUpperCase()

        return modifiedRoute
    }

    fun getRoutesByUserId(userId: Int): Set<String> {

        val routesByUserId = mutableSetOf<String>()

        /*
        Since each user can curate their routes online or via their phone I assume this
        would do an API call to each Service with a response containing the routes.
        Following is an attempt to replicate this with an appearance of randomness.
        */
        if (userId % 2 > 0) {
            routes.mapTo(routesByUserId, { modifyRouteWithUserId(it, userId) })
        } else {
            routesByUserId.add( modifyRouteWithUserId( routes.elementAt(userId % 3), userId) )
        }

        return routesByUserId
    }

}