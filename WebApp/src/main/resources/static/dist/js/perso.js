		$(document).ready(function() {
			$('#example').DataTable({
				"language" : {
					"lengthMenu" : "Nombre d'entrées _MENU_",
					"search" : "Recherche",
					"info" : "Affichage de _START_ à _END_ de _TOTAL_ entrées",
					"infoEmpty" : "Affichage de 0 à 0 à 0 entrées",
					"paginate" : {
						"first" : "Début",
						"last" : "Fin",
						"next" : "Suivant",
						"previous" : "Précédent"
					},
					"zeroRecords" : "Aucun résultat trouvé",
					"emptyTable" : "Cette table ne contient aucune entrée",
				},
				responsive : true
			});
		});
