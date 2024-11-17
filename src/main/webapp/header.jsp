    <!-- Inclure l'en-tête -->
    <header class="p-3">
        <div class="container">
            <div class="d-flex justify-content-between">
                <div class="logo">
                    <h1><a class="icon-link" href="${pageContext.request.contextPath}/">onlineshop</a></h1>
                </div>
                <nav>
                    <ul class="nav">
                        <li class="nav-item"><a class="nav-link text-dark"  href="${pageContext.request.contextPath}/"><b>Accueil</b></a></li>
                        <li class="nav-item"><a class="nav-link text-dark" href="#"><b>Contacter Nous</b></a></li>
                        <li class="nav-item"><a class="nav-link text-dark" href="#"><b>FR</b></a></li>
                    </ul>
                </nav>
                <div class="search-bar">
				    <form method="get" action="<%= request.getContextPath() %>/">
				        <input type="text" class="form-control" placeholder="Recherche..." name="search" value="${param.search}">
				    </form>
				</div>
                <div class="d-flex">
                    <a href="#" class="btn">Panier</a>
                    <a href="${pageContext.request.contextPath}/auth?action=login" class="btn">Se connecter</a>
                </div>
            </div>
            <hr>
        </div>
    </header>