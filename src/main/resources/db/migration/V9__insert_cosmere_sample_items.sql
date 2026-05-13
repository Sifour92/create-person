-- ============================================================
-- V9: Cosmere RPG — Sample Items
-- Стартовые данные: ancestry, culture, path, action
-- ============================================================

-- ANCESTRY: Human
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Human', 'ANCESTRY', 'human',
        'modules/cosmere-rpg/assets/ancestries/human.webp',
        'Most of Roshar is divided into human-ruled nations.',
        '{
          "size": "medium",
          "advancement": {
            "extraPath": null,
            "bonusTalents": [],
            "extraTalents": []
          }
        }');

-- ANCESTRY: Singer
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Singer', 'ANCESTRY', 'singer',
        'modules/cosmere-rpg/assets/ancestries/singer.webp',
        'The indigenous people of Roshar, singers are a diverse species.',
        '{
          "size": "medium",
          "advancement": {
            "extraPath": null,
            "bonusTalents": [],
            "extraTalents": []
          }
        }');

-- CULTURE: Alethi
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Alethi', 'CULTURE', 'alethi',
        'modules/cosmere-rpg/assets/cultures/alethi.webp',
        'Alethkar is ruled by a monarch and ten highprinces, driven by war and conquest.',
        '{
          "events": {
            "add": {
              "type": "grant-expertises",
              "expertises": {
                "cultural:alethi": {"id": "alethi", "type": "cultural", "label": "Alethi"}
              }
            },
            "remove": {
              "type": "remove-expertises",
              "expertises": {
                "cultural:alethi": {"id": "alethi", "type": "cultural", "label": "Alethi"}
              }
            }
          }
        }');

-- CULTURE: Thaylen
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Thaylen', 'CULTURE', 'thaylen',
        'modules/cosmere-rpg/assets/cultures/thaylen.webp',
        'Thaylenah is a nation of seafarers and merchants.',
        '{
          "events": {
            "add": {
              "type": "grant-expertises",
              "expertises": {
                "cultural:thaylen": {"id": "thaylen", "type": "cultural", "label": "Thaylen"}
              }
            },
            "remove": {
              "type": "remove-expertises",
              "expertises": {
                "cultural:thaylen": {"id": "thaylen", "type": "cultural", "label": "Thaylen"}
              }
            }
          }
        }');

-- HEROIC PATH: Agent
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Agent', 'PATH', 'agent',
        'modules/cosmere-rpg/assets/paths/agent.webp',
        'Masters of deception and sabotage. Specialties: Investigator, Spy, Thief.',
        '{
          "pathType": "heroic",
          "startingSkill": "ins",
          "specialties": ["investigator", "spy", "thief"]
        }');

-- HEROIC PATH: Hunter
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Hunter', 'PATH', 'hunter',
        'modules/cosmere-rpg/assets/paths/hunter.webp',
        'Trackers and survivalists who excel in the wilderness.',
        '{
          "pathType": "heroic",
          "startingSkill": "sur",
          "specialties": ["trapper", "stalker", "warden"]
        }');

-- ACTION: Drop
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Drop', 'ACTION', 'drop',
        'icons/skills/social/wave-halt-stop.webp',
        'You drop any number of items held in your hands.',
        '{
          "activation": {
            "type": "utility",
            "cost": {"value": null, "type": "free"}
          },
          "damage": {"formula": null, "type": null}
        }');

-- ACTION: Move
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Move', 'ACTION', 'move',
        'icons/skills/movement/feet-winged-boots-brown.webp',
        'You move up to your Speed in spaces.',
        '{
          "activation": {
            "type": "utility",
            "cost": {"value": 1, "type": "act"}
          },
          "damage": {"formula": null, "type": null}
        }');

-- ACTION: Strike
INSERT INTO cosmere.items (name, type, item_key, img, description_short, system_data)
VALUES ('Strike', 'ACTION', 'strike',
        'icons/skills/melee/blade-tip-orange.webp',
        'You attack a creature or object within your reach.',
        '{
          "activation": {
            "type": "attack",
            "cost": {"value": 1, "type": "act"}
          },
          "damage": {"formula": "1d6", "type": "impact"}
        }');
