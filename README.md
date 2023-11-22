# Huffman
Huffman Encoding

This is a project containing three different .java files: Node.java, huffmanEncoding.java and main.java.

The goal of the project is to generate a random string and encode then decode it utilizing huffman encoding. This is achieved by first looping through the randomly generated string and counting how many times each character appears, building a hashmap to store this information. Then each element in the hashmap is made into a node and stored into an array. Using the already made nodes the tree is built with less common characters requiring more paths to get to, while the most common characters would only have a couple.
