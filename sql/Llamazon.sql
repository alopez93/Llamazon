UNLOCK TABLES;
DROP TABLE IF EXISTS `LamazonBooksToUsers`;
DROP TABLE IF EXISTS `LamazonBooks`;

DROP TABLE IF EXISTS `LlamazonBooks`;
CREATE TABLE `LlamazonBooks` (
	`llamazonBookId` INT NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(30) NOT NULL,
	`description` VARCHAR(3000) NOT NULL,
	`bookImage` VARCHAR(50) NOT NULL,
	`author` VARCHAR(50) NOT NULL,
    PRIMARY KEY (llamazonBookId)
);

DROP TABLE IF EXISTS `LlamazonBooksToUsers`;
CREATE TABLE `LlamazonBooksToUsers` (
    `id` INT NOT NULL AUTO_INCREMENT,
	`llamazonBookId` INT NOT NULL,
	`bookKey` VARCHAR(90) NOT NULL UNIQUE,
	`userCode` VARCHAR(30) NOT NULL,
	`bookFormat` VARCHAR(30) NOT NULL,
    PRIMARY KEY (id),
	CONSTRAINT `llamazonbookiduser_llamazonbook` FOREIGN KEY (`llamazonBookId`)
        REFERENCES `LlamazonBooks` (`llamazonBookId`)
        ON DELETE CASCADE ON UPDATE CASCADE
);

LOCK TABLES `LlamazonBooks` WRITE;
INSERT INTO `LlamazonBooks` VALUES (1, 'Test Book One', 'Description', 'imagePath', 'author'), 
								  (2, 'Test Book Two', 'Description', 'imagePath', 'author'), 
								  (3, 'Test Book Three', 'Description', 'imagePath', 'author'), 
								  (4, 'Test Book Four', 'Description', 'imagePath', 'author');
UNLOCK TABLES;