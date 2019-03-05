# AEM Components

This project contains standard AEM components such as Header, Footer, Carousel, Contact Form, Component to Read .

## Modules
Components are located under aem-website/ui.apps/src/main/content/jcr_root/apps/aem.website/components/content

Services and model are located under aem-website/core


##  Components

The project contains 5 Components 

*Navigation 
**Navigation compoenent is based on Bootstrap Header  Navigation with menu, submenu and a search feature which can be configured

*Footer
**Footer component is based on bootstrap footer with column as  multifield and column item as a nested mulitfield 

*Contact Form
**contact form component is a form which saves data to mongo DB database, MongoDB hostname and port number can be configured under "aem-website/core/src/main/java/com/aem/website/core/impl/SaveDataServiceimpl.java"

*ReadDBdata 
**ReadDB data component fetches value from mongoDB database that was stored through contact form component MongoDB hostname and port number can be configured under "aem-website/core/src/main/java/com/aem/website/core/impl/ReadDataServiceimpl.java"
