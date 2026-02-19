class-diagram
    class HttpSessionListener {
        <<interface>>
        +sessionCreated(HttpSessionEvent)
        +sessionDestroyed(HttpSessionEvent)
    }

    class SessionListener {
        +sessionCreated(HttpSessionEvent session)
        +sessionDestroyed(HttpSessionEvent se)
    }

    class Component {
        <<annotation>>
    }

    HttpSessionListener <|.. SessionListener : implements
    Component ..> SessionListener : annotates
