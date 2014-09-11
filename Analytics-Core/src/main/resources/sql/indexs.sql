ALTER TABLE `analytics`.`app` 
ADD INDEX `uid` (`user_id` ASC) ;

ALTER TABLE `analytics`.`app` 
ADD INDEX `name` (`name` ASC) ;

ALTER TABLE `analytics`.`usr` 
ADD INDEX `name` (`name` ASC) ;


ALTER TABLE `analytics`.`event` 
ADD INDEX `app` (`app_id` ASC) ;

ALTER TABLE `analytics`.`event` 
ADD INDEX `name` (`name` ASC) ;

ALTER TABLE `analytics`.`label` 
ADD INDEX `event` (`event_id` ASC) ;

ALTER TABLE `analytics`.`label` 
ADD INDEX `name` (`name` ASC) ;

ALTER TABLE `analytics`.`label` 
ADD INDEX `model` (`model_id` ASC) ;

ALTER TABLE `analytics`.`model` 
ADD INDEX `model` (`model` ASC) ;

ALTER TABLE `analytics`.`model` 
ADD INDEX `name` (`name` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `label` (`label_id` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `year` (`year` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `month` (`month` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `day` (`day` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `hour` (`hour` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `type` (`type` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `create` (`gmt_created` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `modify` (`gmt_modified` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `ly` (`label_id` ASC, `year` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `lym` (`label_id` ASC, `year` ASC, `month` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `lymd` (`label_id` ASC, `year` ASC, `month` ASC, `day` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `lymdh` (`label_id` ASC, `year` ASC, `month` ASC, `day` ASC, `hour` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `lyt` (`label_id` ASC, `year` ASC, `type` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `lymt` (`label_id` ASC, `year` ASC, `month` ASC, `type` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `lymdt` (`label_id` ASC, `year` ASC, `month` ASC, `day` ASC, `type` ASC) ;

ALTER TABLE `analytics`.`stats` 
ADD INDEX `lymdht` (`label_id` ASC, `year` ASC, `month` ASC, `day` ASC, `hour` ASC, `type` ASC) ;


