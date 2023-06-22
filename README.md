
# Password generator

Simple small application with GUI interface where we can generate passwords using few parameters like:

- using small or big letters (or both)
- using numbers or special characters (or both)
- length of a password (8-24)

Every password that will be created is hashed using SHA-256 and hex representation is saved in a file. To ensure uniqueness, before displaying newly generated password, application checks if hex value doesn't already exists in a file, if it does new password will be created as long as is finally unique.

Generated password is displayed in a dialog box with editible text field where user can copy or modify it to its like. 

Application covers 3 languages through use of resource bundle (polish,english,german), but german one is translated through google translate, so for any errors i apoligize.


## Screenshots

[![HPRZbHv.png](https://iili.io/HPRZbHv.png)](https://freeimage.host/pl)

[![HPRZmRR.png](https://iili.io/HPRZmRR.png)](https://freeimage.host/pl)

[![HPRZyDN.png](https://iili.io/HPRZyDN.png)](https://freeimage.host/pl)

[![HPRZpNp.md.png](https://iili.io/HPRZpNp.md.png)](https://freeimage.host/i/HPRZpNp)

