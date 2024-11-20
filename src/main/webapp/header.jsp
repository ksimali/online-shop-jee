    <!-- Inclure l'en-tête -->
    <header class="p-3">
        <div class="container">
            <div class="d-flex justify-content-between">
                <div class="logo">
                    <h1><a class="icon-link text-white" href="${pageContext.request.contextPath}/">onlineshop</a></h1>
                </div>
                <nav>
                    <ul class="nav">
                        <li class="nav-item"><a class="nav-link text-white"  href="${pageContext.request.contextPath}/"><b>Accueil</b></a></li>
                        <li class="nav-item"><a class="nav-link text-white" href="#"><b>Contacter Nous</b></a></li>
                        <li class="nav-item"><a class="nav-link text-white" href="#"><b>FR</b></a></li>
                    </ul>
                </nav>
                <div class="search-bar">
				    <form method="get" action="<%= request.getContextPath() %>/">
				        <input type="text" class="form-control" placeholder="Recherche..." name="search" value="${param.search}">
				    </form>
				</div>
                <div class="d-flex">
                    <a href="${pageContext.request.contextPath}/panier" class="btn">Panier</a>
                   <c:choose>
                    <c:when test="${not empty userName}">
                        <%-- Si l'utilisateur est connecté, affichez son nom --%>
                        <a href="${pageContext.request.contextPath}/auth?action=gcompte" class="btn">${userName}</a>
                		<a href="${pageContext.request.contextPath}/auth?action=logout" class="btn">Logout</a>                        
                    </c:when>
                    <c:otherwise>
                        <%-- Sinon, affichez le bouton de connexion --%>
                        <a href="${pageContext.request.contextPath}/auth?action=login" class="btn">Se connecter</a>
                    </c:otherwise>
                </c:choose>
                </div>
            </div>
            <hr>
        </div>
    </header>