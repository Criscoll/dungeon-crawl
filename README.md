# Project

You have received a request from a client for an application for the playing of dungeon-style puzzles. With a partner from your lab class, you will follow an agile development process to design and implement a desktop Java application that satisfies the requirements of the client (see below). The final piece of software you deliver is expected to be of professional quality, user-friendly, and demonstrate the knowledge and skills you have acquired in this course.

### Dungeon layout

To be specific, the layout of each dungeon is defined by a grid of squares, each of which may contain one or more entities. The different types of entities are as follows:

| Entity               | Example                                 | Description                                                                                                                                                                                                                                                                   |
| -------------------- | --------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Player               | ![Player][player]                       | Can be moved up, down, left, and right into adjacent squares, provided another entity doesn't stop them (e.g. a wall).                                                                                                                                                        |
| Wall                 | ![Wall][wall]                           | Blocks the movement of the player, enemies and boulders.                                                                                                                                                                                                                      |
| Exit                 | ![Exit][exit]                           | If the player goes through it the puzzle is complete.                                                                                                                                                                                                                         |
| Treasure             | ![Treasure][treasure]                   | Can be collected by the player.                                                                                                                                                                                                                                               |
| Door                 | ![Door][door_open] ![Door][door_closed] | Exists in conjunction with a single key that can open it. If the player holds the key, they can open the door by moving through it. Once open it remains so. The client will be satisfied if dungeons can be made with up to 3 doors.                                         |
| Key                  | ![Key][key]                             | Can be picked up by the player when they move into the square containing it. The player can carry only one key at a time, and only one door has a lock that fits the key. It disappears once it is used to open its corresponding door.                                       |
| Boulder              | ![Boulder][boulder]                     | Acts like a wall in most cases. The only difference being that it can be pushed by the player into adjacent squares. The player is only strong enough to push **one** boulder at a time.                                                                                      |
| Floor switch         | ![Floor switch][switch]                 | Switches behave like empty squares, so other entities can appear on top of them. When a boulder is pushed onto a floor switch, it is triggered. Pushing a boulder off the floor switch untriggers it.                                                                         |
| Portal               | ![Portal][portal]                       | Teleports entities to a corresponding portal.                                                                                                                                                                                                                                 |
| Enemy                | ![Enemy][enemy]                         | Constantly moves toward the player, stopping if it cannot move any closer. The player dies upon collision with an enemy.                                                                                                                                                      |
| Sword                | ![Sword][sword]                         | This can be picked up the player and used to kill enemies. Only one sword can be carried at once. Each sword is only capable of 5 hits and disappears after that. One hit of the sword is sufficient to destroy any enemy.                                                    |
| Invincibility potion | ![Invincibility][invincibility]         | If the player picks this up they become invincible to enemies. Colliding with an enemy should result in their immediate destruction. Because of this, all enemies will run away from the player when they are invincible. The effect of the potion only lasts a limited time. |

### Input

The application will read from a JSON file containing a complete specification of the dungeon (the initial position of entities, goal, etc.). Example dungeons are included in the `dungeons` directory and the starter code contains an incomplete dungeon loader.

The dungeon files have the following format:

> { "width": _width in squares_, "height": _height in squares_, "entities": _list of entities_, "goal-condition": _goal condition_ }

Each entity in the list of entities is structured as:

> { "type": _type_, "x": _x-position_, "y": _y-position_ }

where _type_ is one of

> ["player", "wall", "exit", "treasure", "door", "key", "boulder", "switch", "portal", "enemy", "sword", "invincibility"]

The `door`, `key`, and `portal` entities include an additional field `id` containing a number. Keys open the door with the same `id` (e.g. the key with `id` 0 opens the door with `id` 0). Portals will teleport entities to the **one** other portal with the same ID.

The goal condition is a JSON object representing the logical statement that defines the goal. Basic goals are:

> { "goal": _goal_ }

where _goal_ is one of

> ["exit", "enemies", "boulders", "treasure"]

In the case of a more complex goal, _goal_ is the logical operator and the additional _subgoals_ field is a JSON array containing subgoals, which themselves are goal conditions. For example,

```JSON
{ "goal": "AND", "subgoals":
  [ { "goal": "exit" },
    { "goal": "OR", "subgoals":
      [ {"goal": "enemies" },
        {"goal": "treasure" }
      ]
    }
  ]
}
```

Note that the same basic goal _can_ appear more than once in a statement.

You can extend this format to include additional information if you wish, but your application should still work with files in the original format.

### User interface

The UI component of this project has been implemented in JavaFX. The examples above came from [here](http://opengameart.org).

[player]: images/human_new.png
[wall]: images/brick_brown_0.png
[exit]: images/exit.png
[door_open]: images/open_door.png
[door_closed]: images/closed_door.png
[key]: images/key.png
[boulder]: images/boulder.png
[switch]: images/pressure_plate.png
[portal]: images/portal.png
[enemy]: images/deep_elf_master_archer.png
[sword]: images/greatsword_1_new.png
[invincibility]: images/brilliant_blue_new.png
[treasure]: images/gold_pile.png
[maze]: examples/maze.png
[boulders]: examples/boulders.png
[advanced]: examples/advanced.png
