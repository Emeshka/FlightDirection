# FlightDirection - web site about travelling

This project is my test of bundling Java Spring Boot with PostgreSQL and React front-end part.

Data model for now:

	Destination
	|---Country
	|---Town
	Article
	FileBlob

Towns and countries are combined with an Entity class Destination (JOINED strategy of inheritance was chosen). Country also has a OneToOne relation to the capital Town.
Articles should be attached to one and only destination. FileBlobs - as well, but Country also has extra OneToOne relation with FileBlob as flag.

![Screenshot_19](https://user-images.githubusercontent.com/65328222/158078612-fd678c3b-f70f-46d8-bf16-b9135bd7553b.png)

API for now:

Get summary list of towns in given language. Language affects the title and textSnippet fields. If a town doesn't have an article in the provided language, the town will still be in the list, but title will be equal to the nameTransliterated and the textSnippet will be empty. It will be used to ask user if he wants to see an article in a different language anyway.

	@GetMapping("/api/towns/{lang}")
	public ResponseEntity<List<ResponseTownPreview>> getTowns(
		@PathVariable String lang
	)

Create a town. It's created with an original name only, other fields should be edited through further PUT requests. By the original name the name in the native language and script is meant, it will then be transliterated and used in React Routing to make more informative URLs (Αθήνα => Athena, 北京 => Bei Jing).

	@PostMapping("/api/town")
	public ResponseEntity<ResponseNewDestination> createTown(
		@RequestParam("nameOriginal") String nameOriginal
	)

Upload a file as a byte array.

	@PostMapping("/api/upload")
	public ResponseEntity<ResponseMessage> uploadFile(
		@RequestParam("file") MultipartFile file
	)

Get a list of all files (temporary).

	@GetMapping("/api/files")
	public ResponseEntity<List<ResponseFile>> getListFiles()

Get a file by id. Returns Base64 string.

	@GetMapping("/api/file/{id}")
	public ResponseEntity<String> getFile(@PathVariable String id)

Create or update an article given destination id & language. Returns the article.

	@PutMapping("/api/destination/article/{lang}/{destination_id}")
	public ResponseEntity<ResponseArticle> addArticle(
		@PathVariable String lang,
		@PathVariable Long destination_id,
		@RequestParam String title,
		@RequestParam String text
	)

Create a country. It's created with an original name only, other fields should be edited through further PUT requests.

	@PostMapping("/api/country")
	public ResponseEntity<ResponseNewDestination> createCountry(
		@RequestParam("nameOriginal") String nameOriginal
	)

Get summary list of countries in given language. Language affects the title and textSnippet fields. If a country doesn't have an article in the provided language, the country will still be in the list, but title will be equal to the nameTransliterated and the textSnippet will be empty. It will be used to ask user if he wants to see an article in a different language anyway.

	@GetMapping("/api/countries/{lang}")
	public ResponseEntity<List<ResponseCountryPreview>> getCountries(
		@PathVariable String lang
	)

Get the article with given language and destination id. Returns an object, if nothing is found - fields of the object are null.

	@GetMapping("/api/article/{lang}/{destination_id}")
	public ResponseEntity<ResponseArticle> getArticle(
		@PathVariable String lang,
		@PathVariable Long destination_id
	)

Quick memo to self about the stack:

- Webpack, Babel to pack the front-end to bundle.js in resources folder.
- React, Axios
- Spring Boot Web
- Spring Boot JPA, Hibernate
- PostgreSQL
