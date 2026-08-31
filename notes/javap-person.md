1. The constructor bytecode invokes the Object initialization method, sets the field name and types, and then returns.
2. The display bytecode gets the PrintStream field, loads the string, loads the object, gets the fields, invokes the integer, invokes the print stream, pops, and returns.
3. Three opcodes I saw are: dup, anewarray, aastore