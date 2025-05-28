# Product_Recommendation_System
In this project, I developed a text-based recommendation system for products, built on a directed graph structure.
The system models products and categories as nodes, with semantic relationships such as contains, part-of, or successor-of represented as edges.
Recommendations are computed by traversing this graph structure.
## Architecture & Implementation
The project follows a modular design based on the MVC principle, with clearly separated responsibilities:

model: Defines core data structures including Product, Category, Edge, Graph, and recommendation Term logic

commands: Implements the command pattern for actions such as add, remove, recommend, export

ui: Contains parsers for user input (RecommendationParser, EdgeParser) and a custom exception (ParseException)

main: The Recommender class serves as the entry point and runs the user interaction loop
