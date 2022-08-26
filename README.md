# Interactive Aquarium (WIP)
**A place where fish can be themselves!**

### What will the application do?

> The application will animate happy fish in the background against 
> a relaxing backdrop to provide a peaceful visual to the user.

### Who will use it?

> People who like fish, aquariums, and would like to experience
> the joys of raising fish without the associated havoc!

### Why is the project of interest to you?

> I love fish, especially for the idea that they have their own world in which
> you can kind of let them be and do their own thing. I love the idea of simulating 
> a basic "sentience" of the fish through some very simple animations and
> programming to where it feels like the fish has some kind of personality.

# As a **user**, I want to be able to:
- Add fish to a Tank with chosen attributes of Name, Colour, Species
- Remove fish from a Tank
- Feed the fish to reset their hunger levels
- Watch the fish swim around
- Check the hunger levels of the fish
- Have the tank auto-save when I decide to quit the application
- When starting the application, have the option to either continue with the previous tank, or start over

# If I have the time, I would love to add extra functionalities like:
- Being able to "chase" fish with the cursor
- Providing basic animations for the fish
- Provide some decorations to drag and drop around the tank
- Having dirtiness accumulate + cleaning of the tank
- Provide code for the fish to "eat" the food and swim towards it
- Provide avenues to customize the tank and the background
- Provide avenues to customize the fish
- Have fish express their level of "contentment"
- Have fish die if too unhappy
- Some degree of gamification where upgrades can be purchased, like an automatic feeder
- Have fish interact with each other i.e. Prey fish gather in schools away from predators

# Instructions for Grader

- You can generate the first required event by clicking "Add Fish", and entering the fish details.
- You can generate the second required event by clicking "Remove Fish", and entering the name of the fish to remove.
- You can locate my visual component by looking at the blue Tank area on the left, and adding fish to swim in it.
- You can save the state of my application by clicking the "Save Fish" button.
- You can reload the state of my application with the pop-up on startup if there is a previous save file to load from.

# Phase 4: Task 2
Here is an example of an event log you might expect: 

- Tue Aug 09 21:09:39 PDT 2022 
- A fish named Nemo was added to the tank.
- Tue Aug 09 21:09:45 PDT 2022
- A fish named Dory was added to the tank.
- Tue Aug 09 21:09:49 PDT 2022
- The fish Nemo was removed from the tank.

# Phase 4: Task 3
I gave the hierarchy design a lot of thought in Phase 2, and I don't have too many classes to begin with, so I was
pretty satisfied with the design. I considered the addition of a MenuPanel class, not unlike the TankPanel class
that I already have, as I felt like a lot of the button generating and handling was bloating the GUI a little,
but I personally felt that it was reasonably within the lines of what the TankAppGUI class should be handling anyway.
