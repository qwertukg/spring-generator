# Kotlin Spring Rest Api basic code generator

### Can make some basic code for you

Just make a simple `config.json` file, show path of config to generator, and set destination folder.

**Generator will make next structure:**
- models
- controllers
- repositories
- application
- application.properties

## Sample of `config.json`
```json
{
	"name": "shop",
	"models": [
		{
			"name": "Product",
			"properties": [
				{"name": "title", "type": "String"},
				{"name": "cost", "type": "Double"},
				{"name": "amount", "type": "Int"},
				{"name": "category", "type": "Category"}
			]
		},
		{
			"name": "Category",
			"properties": [
				{"name": "title", "type": "String"},
				{"name": "time", "type": "Long"},
				{"name": "products", "type": "List<Product>"}
			]
		}
	],
	"config": [
		{
			"name": "spring.datasource.driver-class-name", 
			"value": "com.mysql.cj.jdbc.Driver"
		},
		{
			"name": "spring.datasource.url", 
			"value": "jdbc:mysql://127.0.0.1:3306/shop_db?serverTimezone=UTC&useSSL=false"
		},
		{
			"name": "spring.datasource.username", 
			"value": "root"
		},
		{
			"name": "spring.datasource.password", 
			"value": "1234"
		}
	]
}
```

## Killer feature
Run generator with `-p` parameter, and you can see generated content before saving :)

