This app is another form for users being able to express their emotions and thoughts privately. 
We want this app to be easy to use and aesthetically pleasing, while keeping in mind accessibility.

Everything that is important towards the project will be listed below along with resources that
helped guide us on this journey. 

As of right now, we added in a very basic structure for our app.
We are still trying to figure out how we want to organize things because we want to get the basic
components down first.
We also want to add a data library to help store the journal entries. We don't have a specific
one in mind as of right now, but that is a library that we would want to implement since it will
help us keep track of the journal entries that users put in. 
Additionally, we will also be using the material component library from Google to have icons and
designs for our app.

Components that we need to create:
- Text box that user uses to journal (Done)
  - Button to save entries for each day (Done)
- Calendar view
  - Utilized the LocalDate class from the java.time package (Done)
- Bottom bar navigation (Done)
  - Create proper content descriptions for each button/icon! (Done)
  - Home button (Done)
  - Calendar button (Done)
  - Journal button (Done)
  - Settings button -> customization (Done)

Design/visual things: 
- Create a start screen that appears when users first click on app (Done)
  - Create logo design (Done)
  - Create logo text -> Going to use text font 
  - Utilized the Android Compose's Animation package in order for logo animation to work (Done)
- Home Screen
  - Possible ideas:
    - Today's date (Done)
    - A random positive message (Done)
    - 
- Journal Screen
  - Text box for journal entry (Done)
  - Mood -> emojis (Done)
  - Add pictures feature
  - Share or private (Not a priority)
  - Different journals (Not a priority)
- Calendar Screen
  - Month view (Done)
  - Which days have user create entry and days they have not 
  - Click on date to go to specific entry
- Settings Screen (Not a priority)
  - Added a name input box 

Backend: 
- Journal Screen
Kind of done. We utilized Android Jetpack's Data Store to help with this.
We were able to figure out how to get input to be stored after the app closes but we are still
trying to figure out how to connect to the calendar and get that to show for which days are done.
  - Figure out a way to get the entries to save
  - https://developer.android.com/topic/libraries/architecture/datastore

Color: Check the colors.xml file for extended list
Starting colors can be found here for easy access. 
- Purple: #a49ec8
- Light Green: #c0e1b0
- Green: #85af85
- Pink: #ffc1d2
- Light Yellow: #fff9e5
- Yellow: #e1d675
- Light Blue: #b8e0f6
- Dark green: colorResource(R.color.md_theme_light_onPrimaryContainer)

Useful guides/links:
- Layouts UI
  - https://developer.android.com/codelabs/jetpack-compose-layouts#7
  - https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#surfaces-and-layout
  - https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary
- Color builder
  - https://m3.material.io/theme-builder
- Icon 
  - https://fonts.google.com/icons
- Animation
  - Fade in/out
  - https://developer.android.com/jetpack/compose/animation/composables-modifiers
- Font
We tried implementing a new font into our project, but we were unsuccessful. 
  - https://developer.android.com/jetpack/compose/text/fonts

*Note: We tried implementing an xml file for the layout but we were unable to fully figure out how
to get it to work, but we left the files in here anyways for future reference. 
# Mindful-Memo
