class Service(
        val name: String,
        // Instructions state the routes are fixed for this exercise,
        // therefore we are sticking with an immutable collection for routes.
        // I imagine in the Karoo the routes would be mutable.
        val routes: Set<String>,
        val modifier: (userId: Int, route: String) -> String = {_,_ -> ""}) {

    fun getRoutesByUserId(userId: Int): Set<String> {

        val routesByUserId = mutableSetOf<String>()

        /*
        Since each user can curate their routes online or via their phone I assume this
        would do an API call to each Service with a response containing the routes.
        Following is an attempt to replicate this with an appearance of randomness.
        */
        if (userId % 2 > 0) {
            routes.mapTo(routesByUserId, { modifier(userId, it) })
        } else {
            routesByUserId.add( modifier( userId, routes.elementAt(userId % 3) ) )
        }

        return routesByUserId
    }

}