delete from material;
delete from period;
delete from price;
delete from service;
insert into material values (1,'Squishy leaves','Probably a little old'),(2,'Moldy bread','Pretty yeasty'),(3,'Shredded sticks','Destroyer of lawn mowers'),(4,'Lawn trimmings','Bagged up and smelling great'),(5,'Decaying leaves','Raked and smelling pungent'),(6,'Veggie peels','Saved the best part for stew'),(7,'Citrus rinds','Zesty'),(8,'Cherry stems','Neatly knotted'),(9,'Expired yogurt','Culture in a fuzzy coat'),(10,'Moldy cheese','Advanced civilization');
INSERT INTO period VALUES (1,1,'Day'),(2,2,'Day'),(3,1,'Week'),(4,2,'Week'),(5,1,'Month'),(6,2,'Month'),(7,6,'Month'),(8,1,'Year'),(9,90,'Day');
INSERT INTO price VALUES (1,1.99,'pound'),(2,3.29,'gallon'),(3,5.24,'cubic foot');
INSERT INTO service VALUES (1,'Deliver Bin','A composting bin will be delivered to your front doorstep'),(2,'Bin Pick-Up','Your composting bin will be scheduled for pick-up'),(3,'How-To Guide','A guide will help you understand how to compost'),(4,'Compostable Materials Guide','A guide that will show you what are compostable materials and what should not be added to your compost bin'),(5,'Troubleshooting Guide','A guide that will help you troubleshoot your compost bin'),(6,'Track your impact','A tool used to track your impact to see how much food you have diverted from landfills'),(7,'Composting Domino Effect Guide','A guide that explains the importance of composting and all of the benefits that come from composting'),(8,'Bin Size','Choose a bin size right for your house');

