# spring-food
back-end component

This is a non Spring Boot configuration of an example server that uses HIBERNATE to reduce the effort of data persistence.
It is focused in recipes.
It uses a Spoonacular api client to get recipes, besides the possibility to add new recipes.

MySql 8.0.19 is used.

Sql database script can be found in "spring-food.sql" file.

Properties relating to persistence can be found in the file "persistence-mysql.properties".

## instructions

The server can be started very conveniently trough the Maven command from the IDE:

clean install cargo:run

### default configuration
endpoint: localhost:8081

##
### service locations
##
services are protected by Spring Security against CSRF
##
-index: @GET localhost:8081/ (redirect to localhost:8081/index.html);

-login processing: @POST localhost:8081/authenticate

example request:

curl --location --request POST 'http://localhost:8081/authenticate' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=admin' \
--data-urlencode 'password=admin' \
--data-urlencode '_csrf=[take this token from index.html]'

-register account processing: @POST localhost:8081/register

example request:

curl --location --request POST 'http://localhost:8081/register' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=user' \
--data-urlencode 'password=user' \
--data-urlencode 'confirmpassword=user' \
--data-urlencode 'name=name' \
--data-urlencode 'surname=surname' \
--data-urlencode 'email=email@email.com' \
--data-urlencode '_csrf=[take this token from index.html]'

-logout processing: @POST localhost:8081/logout

example request:

curl --location --request POST 'http://localhost:8081/logout' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode '_csrf=[take this token from index.html]'

-delete account processing: @POST localhost:8081/delete-account (must be logged in first - very basic mechanism)

example request:

curl --location --request POST 'http://localhost:8081/delete-account' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=user' \
--data-urlencode '_csrf=[take this token from index.html]'

##
### api service locations
##
api services are protected by Spring Security against CSRF and the user must be authenticated

some services require "ADMIN" level authority
##
-find recipes: @GET localhost:8081/spring-food-api/recipes/{recipeName}?searchType={searchType}
searchType = 1 -> recipeName will work as ingredient name (search recipes by ingredient name)

-find recipe details: @GET localhost:8081/spring-food-api/recipeDetail/{recipeBaseId}

-delete recipe: @DELETE localhost:8081/spring-food-api/recipeDelete/{recipeBaseId} (only "ADMIN" user can access this service)

-create new recipe: @POST localhost:8081/spring-food-api/recipeSave

example request body:

{
	"recipeBase": {
		"title": "Quick and Easy St. Louis-Style Pizza",
		"readyInMinutes": 27,
		"servings": 8,
		"image": "St--Louis-Style-Pizza-492560.jpg"
    },
	"recipeDetail": {
		"vegetarian": false,
		"vegan": false,
		"glutenFree": false,
		"dairyFree": false,
		"veryHealthy": false,
		"cheap": false,
		"veryPopular": true,
		"sustainable": false,
		"lowFodmap": false,
		"weightWatcherSmartPoints": 8,
		"gaps": "no",
		"preparationInMinutes": 10,
		"cookingMinutes": 20,
		"sourceUrl": "https://kitchenconfidante.com/ricotta-pizza-with-prosciutto-and-fresh-pea-salad-recipe",
		"aggregateLikes": 551,
		"popularityScore": 40,
		"healthScore": 3,
		"creditsText": "Kitchen Confidante",
		"sourceName": "Kitchen Confidante",
		"pricePerServing": 149.04,
		"title": "Ricotta Pizza with Prosciutto and Fresh Pea Salad",
		"readyInMinutes": 30,
		"servings": 6,
		"image": "https://spoonacular.com/recipeImages/496972-556x370.jpg",
		"imageType": "jpg",
		"summary": "Ricotta Pizza with Prosciutto and Fresh Pea Salad is a Mediterranean hor d'oeuvre. One portion of this dish contains about <b>11g of protein</b>, <b>17g of fat</b>, and a total of <b>264 calories</b>. For <b>$1.49 per serving</b>, this recipe <b>covers 9%</b> of your daily requirements of vitamins and minerals. This recipe serves 6. This recipe is liked by 551 foodies and cooks. From preparation to the plate, this recipe takes around <b>30 minutes</b>. Head to the store and pick up feta cheese, pizza dough, pepper, and a few other things to make it today. It is brought to you by Kitchen Confidante. Overall, this recipe earns an <b>improvable spoonacular score of 0%</b>. If you like this recipe, take a look at these similar recipes: <a href=\"https://spoonacular.com/recipes/grilled-pizza-with-prosciutto-ricotta-and-arugula-744429\">Grilled Pizza with Prosciutto, Ricottan and Arugula</a>, <a href=\"https://spoonacular.com/recipes/fresh-pea-and-ricotta-tartines-with-spring-vegetables-572331\">Fresh Pean and Ricotta Tartines with Spring Vegetables</a>, and <a href=\"https://spoonacular.com/recipes/fig-and-prosciutto-pizza-with-ricotta-walnuts-and-rosemary-57470\">Fig And Prosciutto Pizza With Ricotta, Walnuts And Rosemary</a>.",
		"instructions": "Instructions\n\nBring a small saucepan of water to a rolling boil. Drop in the fresh English peas, cook for 90 seconds, and strain. In a medium bowl, combine the fresh English peas with the red onion, and toss in the olive oil and white balsamic vinegar. Season to taste with salt and pepper. Set aside to cool in the refrigerator.\n\nPlace a pizza stone in the lowest rack of the oven and preheat the oven to 450F. If using a wood-fired pizza oven, preheat the oven following the manufacturer's instructions.\n\nOn a floured surface, roll out the pizza dough to about 12 inches in diameter. Transfer to a pizza paddle dusted with cornmeal (or a baking sheet lightly greased with olive oil). Press dimples into the dough, as well as a ridge around the perimeter to prevent the cheeses from oozing. Brush the dough lightly with olive oil. In a bowl, mix the ricotta and feta cheeses, and season to taste with salt and pepper. Spread the ricotta mixture over the dough and scatter the prosciutto.\n\nTo bake in oven: Bake for 15-17 minutes, or until the pizza crust is golden.\n\nTo bake in wood-fired pizza oven: Bake for about 30-40 seconds, rotate. Bake for an additional 30-40 seconds, until the crust is evenly cooked.\n\nToss the remaining feta cheese in the pea salad. Top the pizza with the salad and the fresh basil. Slice and serve immediately.",
		"originalId": null,
		"dishTypes": [
			"antipasti",
			"starter",
			"snack",
			"appetizer",
			"antipasto",
			"hor d'oeuvre"
		],
		"cuisines": [
			"Mediterranean",
			"Italian",
			"European"
		],
		"stepOptionList": [
		  [
			{
			  "number": 1,
			  "step": "Bring a small saucepan of water to a rolling boil. Drop in the fresh English peas, cook for 90 seconds, and strain. In a medium bowl, combine the fresh English peas with the red onion, and toss in the olive oil and white balsamic vinegar. Season to taste with salt and pepper. Set aside to cool in the refrigerator.",
			  "lengthNumber": null,
			  "lengthUnit": null
			},
			{
			  "number": 2,
			  "step": "Place a pizza stone in the lowest rack of the oven and preheat the oven to 450F. If using a wood-fired pizza oven, preheat the oven following the manufacturer's instructions.",
			  "lengthNumber": null,
			  "lengthUnit": null
			},
			{
			  "number": 3,
			  "step": "On a floured surface, roll out the pizza dough to about 12 inches in diameter.",
			  "lengthNumber": null,
			  "lengthUnit": null
			},
			{
			  "number": 4,
			  "step": "Transfer to a pizza paddle dusted with cornmeal (or a baking sheet lightly greased with olive oil). Press dimples into the dough, as well as a ridge around the perimeter to prevent the cheeses from oozing.",
			  "lengthNumber": null,
			  "lengthUnit": null
			},
			{
			  "number": 5,
			  "step": "Brush the dough lightly with olive oil. In a bowl, mix the ricotta and feta cheeses, and season to taste with salt and pepper.",
			  "lengthNumber": null,
			  "lengthUnit": null
			},
			{
			  "number": 6,
			  "step": "Spread the ricotta mixture over the dough and scatter the prosciutto.",
			  "lengthNumber": null,
			  "lengthUnit": null
			}
		  ],
		  [
			{
			  "number": 1,
			  "step": "Bake for 15-17 minutes, or until the pizza crust is golden.",
			  "lengthNumber": 17,
			  "lengthUnit": "minutes"
			}
		  ],
		  [
			{
			  "number": 1,
			  "step": "Bake for about 30-40 seconds, rotate.",
			  "lengthNumber": null,
			  "lengthUnit": null
			},
			{
			  "number": 2,
			  "step": "Bake for an additional 30-40 seconds, until the crust is evenly cooked.",
			  "lengthNumber": null,
			  "lengthUnit": null
			},
			{
			  "number": 3,
			  "step": "Toss the remaining feta cheese in the pea salad. Top the pizza with the salad and the fresh basil. Slice and serve immediately.",
			  "lengthNumber": null,
			  "lengthUnit": null
			}
		  ]
		],
		"ingredients": [
		  {
			"aisle": "Produce",
			"image": "peas.jpg",
			"consistency": "solid",
			"name": "peas",
			"original": "1 1/2 cups fresh English peas",
			"originalString": "1 1/2 cups fresh English peas",
			"originalName": "fresh English peas",
			"amount": 1.5,
			"unit": "cups",
			"ingredientMetaList": [
			  "fresh",
			  "english"
			],
			"ingredientMetaInformationList": [
			  "fresh",
			  "english"
			],
			"measure": {
			  "unitShort": "ml",
			  "unitLong": "milliliters",
			  "impUnitShort": "cups",
			  "impUnitLong": "cups"
			}
		  },
		  {
			"aisle": "Produce",
			"image": "red-onion.png",
			"consistency": "solid",
			"name": "red onions",
			"original": "1/2 cup thinly sliced red onions",
			"originalString": "1/2 cup thinly sliced red onions",
			"originalName": "thinly sliced red onions",
			"amount": 0.5,
			"unit": "cup",
			"ingredientMetaList": [
			  "red",
			  "thinly sliced"
			],
			"ingredientMetaInformationList": [
			  "red",
			  "thinly sliced"
			],
			"measure": {
			  "unitShort": "ml",
			  "unitLong": "milliliters",
			  "impUnitShort": "cups",
			  "impUnitLong": "cups"
			}
		  },
		  {
			"aisle": "Oil, Vinegar, Salad Dressing",
			"image": "olive-oil.jpg",
			"consistency": "liquid",
			"name": "extra-virgin olive oil",
			"original": "1-2 tablespoons extra virgin olive oil use best quality you can find",
			"originalString": "1-2 tablespoons extra virgin olive oil use best quality you can find",
			"originalName": "extra virgin olive oil use best quality you can find",
			"amount": 1,
			"unit": "tablespoons",
			"ingredientMetaList": [
			  "canned"
			],
			"ingredientMetaInformationList": [
			  "canned"
			],
			"measure": {
			  "unitShort": "Tbsp",
			  "unitLong": "Tbsp",
			  "impUnitShort": "Tbsp",
			  "impUnitLong": "Tbsp"
			}
		  },
		  {
			"aisle": "Oil, Vinegar, Salad Dressing",
			"image": "vinegar-(white).jpg",
			"consistency": "liquid",
			"name": "white balsamic vinegar",
			"original": "1-2 tablespoons white balsamic vinegar",
			"originalString": "1-2 tablespoons white balsamic vinegar",
			"originalName": "white balsamic vinegar",
			"amount": 1,
			"unit": "tablespoons",
			"ingredientMetaList": [
			  "white"
			],
			"ingredientMetaInformationList": [
			  "white"
			],
			"measure": {
			  "unitShort": "Tbsp",
			  "unitLong": "Tbsp",
			  "impUnitShort": "Tbsp",
			  "impUnitLong": "Tbsp"
			}
		  },
		  {
			"aisle": "Spices and Seasonings",
			"image": "salt.jpg",
			"consistency": "solid",
			"name": "kosher salt",
			"original": "Kosher salt",
			"originalString": "Kosher salt",
			"originalName": "Kosher salt",
			"amount": 1,
			"unit": "serving",
			"ingredientMetaList": null,
			"ingredientMetaInformationList": null,
			"measure": {
			  "unitShort": "serving",
			  "unitLong": "serving",
			  "impUnitShort": "serving",
			  "impUnitLong": "serving"
			}
		  },
		  {
			"aisle": "Spices and Seasonings",
			"image": "pepper.jpg",
			"consistency": "solid",
			"name": "black pepper",
			"original": "Freshly ground black pepper",
			"originalString": "Freshly ground black pepper",
			"originalName": "Freshly ground black pepper",
			"amount": 1,
			"unit": "serving",
			"ingredientMetaList": [
			  "black",
			  "freshly ground"
			],
			"ingredientMetaInformationList": [
			  "black",
			  "freshly ground"
			],
			"measure": {
			  "unitShort": "serving",
			  "unitLong": "serving",
			  "impUnitShort": "serving",
			  "impUnitLong": "serving"
			}
		  },
		  {
			"aisle": "Cheese",
			"image": "feta.png",
			"consistency": "solid",
			"name": "feta cheese",
			"original": "1/4 cup feta cheese",
			"originalString": "1/4 cup feta cheese",
			"originalName": "feta cheese",
			"amount": 0.25,
			"unit": "cup",
			"ingredientMetaList": null,
			"ingredientMetaInformationList": null,
			"measure": {
			  "unitShort": "ml",
			  "unitLong": "milliliters",
			  "impUnitShort": "cups",
			  "impUnitLong": "cups"
			}
		  },
		  {
			"aisle": "Refrigerated",
			"image": "pizza-dough.jpg",
			"consistency": "solid",
			"name": "pizza dough",
			"original": "Pizza dough",
			"originalString": "Pizza dough",
			"originalName": "Pizza dough",
			"amount": 1,
			"unit": "serving",
			"ingredientMetaList": null,
			"ingredientMetaInformationList": null,
			"measure": {
			  "unitShort": "serving",
			  "unitLong": "serving",
			  "impUnitShort": "serving",
			  "impUnitLong": "serving"
			}
		  },
		  {
			"aisle": "Cheese",
			"image": "ricotta.png",
			"consistency": "liquid",
			"name": "skim milk ricotta cheese",
			"original": "1 cup whole milk ricotta cheese",
			"originalString": "1 cup whole milk ricotta cheese",
			"originalName": "whole milk ricotta cheese",
			"amount": 1,
			"unit": "cup",
			"ingredientMetaList": [
			  "whole"
			],
			"ingredientMetaInformationList": [
			  "whole"
			],
			"measure": {
			  "unitShort": "ml",
			  "unitLong": "milliliters",
			  "impUnitShort": "cup",
			  "impUnitLong": "cup"
			}
		  },
		  {
			"aisle": "Cheese",
			"image": "feta.png",
			"consistency": "solid",
			"name": "feta cheese",
			"original": "1/2 cup feta cheese",
			"originalString": "1/2 cup feta cheese",
			"originalName": "feta cheese",
			"amount": 0.5,
			"unit": "cup",
			"ingredientMetaList": null,
			"ingredientMetaInformationList": null,
			"measure": {
			  "unitShort": "ml",
			  "unitLong": "milliliters",
			  "impUnitShort": "cups",
			  "impUnitLong": "cups"
			}
		  },
		  {
			"aisle": "Meat",
			"image": "proscuitto.jpg",
			"consistency": "solid",
			"name": "prosciutto",
			"original": "3 oz smoked prosciutto",
			"originalString": "3 oz smoked prosciutto",
			"originalName": "smoked prosciutto",
			"amount": 3,
			"unit": "oz",
			"ingredientMetaList": [
			  "smoked"
			],
			"ingredientMetaInformationList": [
			  "smoked"
			],
			"measure": {
			  "unitShort": "g",
			  "unitLong": "grams",
			  "impUnitShort": "oz",
			  "impUnitLong": "ounces"
			}
		  },
		  {
			"aisle": "Oil, Vinegar, Salad Dressing",
			"image": "olive-oil.jpg",
			"consistency": "liquid",
			"name": "extra-virgin olive oil",
			"original": "2-3 tablespoons best quality extra virgin olive oil",
			"originalString": "2-3 tablespoons best quality extra virgin olive oil",
			"originalName": "best quality extra virgin olive oil",
			"amount": 2,
			"unit": "tablespoons",
			"ingredientMetaList": null,
			"ingredientMetaInformationList": null,
			"measure": {
			  "unitShort": "Tbsps",
			  "unitLong": "Tbsps",
			  "impUnitShort": "Tbsps",
			  "impUnitLong": "Tbsps"
			}
		  },
		  {
			"aisle": "Produce;Spices and Seasonings",
			"image": "basil.jpg",
			"consistency": "solid",
			"name": "fresh basil leaves",
			"original": "4-5 fresh basil leaves",
			"originalString": "4-5 fresh basil leaves",
			"originalName": "fresh basil leaves",
			"amount": 4,
			"unit": "",
			"ingredientMetaList": [
			  "fresh"
			],
			"ingredientMetaInformationList": [
			  "fresh"
			],
			"measure": {
			  "unitShort": "",
			  "unitLong": "",
			  "impUnitShort": "",
			  "impUnitLong": ""
			}
		  }
		]
	}
}
