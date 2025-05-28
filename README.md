# 🔍 Product_Recommendation_System
In this project, I developed a text-based recommendation system for products, built on a directed graph structure.
The system models products and categories as nodes, with semantic relationships such as contains, part-of, or successor-of represented as edges.
Recommendations are computed by traversing this graph structure.
## 🏗️ Architecture & Implementation
The project follows a modular design based on the MVC principle, with clearly separated responsibilities:

model: Defines core data structures including Product, Category, Edge, Graph, and recommendation Term logic

commands: Implements the command pattern for actions such as add, remove, recommend, export

ui: Contains parsers for user input (RecommendationParser, EdgeParser) and a custom exception (ParseException)

main: The Recommender class serves as the entry point and runs the user interaction loop
## ⚙️ Algorithms & Concepts Used
Depth-First Search (DFS): Used in strategies S2 (successors) and S3 (predecessors) to compute transitive recommendations

Graph Validation & Inverse Relationships: Each added edge automatically inserts its semantic inverse

Regular Expressions (Regex): Used to parse commands like S1 123 or Product1 part-of Product2

TreeSet Sorting: Nodes and edges are sorted alphabetically or by custom predicate order for readable output

Command Pattern: Each user command is encapsulated in its own class (e.g. AddCommand, RecommendCommand)
## 📦 Libraries Used
Pure Java SE (java.util, java.io, java.nio, java.util.regex)

No external frameworks — implementation focuses on clean, maintainable Java code using core object-oriented design principles
