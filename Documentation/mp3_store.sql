-- phpMyAdmin SQL Dump
-- version 3.3.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 05, 2011 at 03:34 PM
-- Server version: 5.1.54
-- PHP Version: 5.3.5-1ubuntu7.2

SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT=0;
START TRANSACTION;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mp3_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `Album`
--
-- Creation: Oct 05, 2011 at 03:05 PM
--

DROP TABLE IF EXISTS `Album`;
CREATE TABLE IF NOT EXISTS "Album" (
  "AlbumID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'AlbumID',
  "BandID" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'BandID',
  "AlbumName" varchar(64) NOT NULL DEFAULT 'Default Album' COMMENT 'AlbumName',
  "AlbumGenre" int(10) unsigned zerofill NOT NULL COMMENT 'AlbumGenre',
  "AlbumDesc" varchar(255) NOT NULL DEFAULT 'No album description' COMMENT 'AlbumDesc',
  "ReleaseDate" date NOT NULL,
  "Rating" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'Rating',
  PRIMARY KEY ("AlbumID"),
  KEY "BandID" ("BandID"),
  KEY "AlbumGenre" ("AlbumGenre"),
  KEY "Rating" ("Rating")
) AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Album`
--

INSERT DELAYED IGNORE INTO `Album` (`AlbumID`, `BandID`, `AlbumName`, `AlbumGenre`, `AlbumDesc`, `ReleaseDate`, `Rating`) VALUES
(0000000001, 0000000001, 'Songs by Bob', 0000000001, '"Songs by Bob" is the first album by the band, "bob". Highly reccomended for rock fans.', '0000-00-00', 0000000010);

-- --------------------------------------------------------

--
-- Table structure for table `Band`
--
-- Creation: Oct 05, 2011 at 03:06 PM
--

DROP TABLE IF EXISTS `Band`;
CREATE TABLE IF NOT EXISTS "Band" (
  "BandID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'BandID',
  "BandManager" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'BandManager',
  "BandName" varchar(64) NOT NULL DEFAULT 'Default Band Name' COMMENT 'BandName',
  "BandDesc" varchar(255) NOT NULL DEFAULT 'No Band Description' COMMENT 'BandDesc',
  "BandGenre" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'BandGenre',
  PRIMARY KEY ("BandID"),
  KEY "BandManager" ("BandManager"),
  KEY "BandGenre" ("BandGenre")
) AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Band`
--

INSERT DELAYED IGNORE INTO `Band` (`BandID`, `BandManager`, `BandName`, `BandDesc`, `BandGenre`) VALUES
(0000000001, 0000000003, 'Bob''s Band', 'Bob''s Band are managed by the exclusive rock supertar "Bob" and make $$$', 0000000001);

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--
-- Creation: Oct 05, 2011 at 03:17 PM
--

DROP TABLE IF EXISTS `Customer`;
CREATE TABLE IF NOT EXISTS "Customer" (
  "CustomerID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'CustomerID',
  "CustomerForename" varchar(25) NOT NULL DEFAULT 'John' COMMENT 'CustomerForename',
  "CustomerSurname" varchar(25) NOT NULL DEFAULT 'Smith' COMMENT 'CustomerSurname',
  "CustomerTitle" varchar(4) NOT NULL DEFAULT 'Mr.' COMMENT 'CustomerTitle',
  "CustomerEmail" varchar(255) NOT NULL DEFAULT 'email@test.com' COMMENT 'CustomerEmail',
  "CustomerAddress" varchar(255) NOT NULL DEFAULT 'Enter an Address' COMMENT 'CustomerAddress',
  "CustomerSince" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'CustomerSince',
  "Verified" tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT 'Verified',
  "MembershipType" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'MembershipType',
  "Password" varchar(128) NOT NULL DEFAULT 'password' COMMENT 'Password',
  PRIMARY KEY ("CustomerID"),
  KEY "MembershipType" ("MembershipType")
) AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Customer`
--

INSERT DELAYED IGNORE INTO `Customer` (`CustomerID`, `CustomerForename`, `CustomerSurname`, `CustomerTitle`, `CustomerEmail`, `CustomerAddress`, `CustomerSince`, `Verified`, `MembershipType`, `Password`) VALUES
(0000000001, 'John', 'Smith', 'Mr.', 'mail@me.com', '1 High Street, Dundee, DD2 123, Scotland', '0000-00-00 00:00:00', 0, 0000000003, 'password'),
(0000000002, 'James', 'Bennet', 'Mr.', 'james@dundee.ac.uk', '123 Dundee Street, Dundeee, DD1 123, Scotland', '2011-10-05 15:20:27', 1, 0000000001, '1234'),
(0000000003, 'Bob', 'Bobski', 'Dr.', 'bob@bob.org', '1 Bob Street, Bobville, TX, USA', '2011-10-05 15:21:56', 1, 0000000002, 'OMG_bob');

-- --------------------------------------------------------

--
-- Table structure for table `Genre`
--
-- Creation: Oct 05, 2011 at 03:12 PM
--

DROP TABLE IF EXISTS `Genre`;
CREATE TABLE IF NOT EXISTS "Genre" (
  "GenreID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'GenreID',
  "GenreName" varchar(25) NOT NULL DEFAULT 'Default Genre' COMMENT 'GenreName',
  "GenreDesc" varchar(255) NOT NULL DEFAULT 'No genre description' COMMENT 'GenreDesc',
  PRIMARY KEY ("GenreID"),
  KEY "GenreName" ("GenreName")
) AUTO_INCREMENT=6 ;

--
-- Dumping data for table `Genre`
--

INSERT DELAYED IGNORE INTO `Genre` (`GenreID`, `GenreName`, `GenreDesc`) VALUES
(0000000001, 'Pop', 'Mainstream music'),
(0000000002, 'Metal', 'Metal, Heavy Metal, Death Metal etc...'),
(0000000003, 'Heavy Rock', 'Alternative rock, such as Punk'),
(0000000004, 'Rock', 'Mainstream rock'),
(0000000005, 'Default Genre', 'No genre description');

-- --------------------------------------------------------

--
-- Table structure for table `MembershipType`
--
-- Creation: Oct 05, 2011 at 03:12 PM
--

DROP TABLE IF EXISTS `MembershipType`;
CREATE TABLE IF NOT EXISTS "MembershipType" (
  "MembershipTypeID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'MembershipTypeID',
  "MembershipName" varchar(25) NOT NULL DEFAULT 'Basic Membership' COMMENT 'MembershipName',
  "MembershipTypeDesc" varchar(255) NOT NULL DEFAULT 'Basic Membership. The most basic membership type we offer.' COMMENT 'MembershipTypeDesc',
  "CanDownload" tinyint(1) unsigned zerofill NOT NULL DEFAULT '1' COMMENT 'CanDownload',
  "CanRedownload" tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT 'CanRedownload',
  "CanUpload" tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT 'CanUpload',
  "CanDownloadUnlimited" tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT 'CanDownloadUnlimited',
  PRIMARY KEY ("MembershipTypeID"),
  KEY "MembershipName" ("MembershipName")
) AUTO_INCREMENT=4 ;

--
-- Dumping data for table `MembershipType`
--

INSERT DELAYED IGNORE INTO `MembershipType` (`MembershipTypeID`, `MembershipName`, `MembershipTypeDesc`, `CanDownload`, `CanRedownload`, `CanUpload`, `CanDownloadUnlimited`) VALUES
(0000000001, 'Premium Membership', 'Go premium to download as much as you want!', 1, 1, 1, 1),
(0000000002, 'Band manager Membership', 'Band manager Membership allows for uploading music', 1, 0, 1, 0),
(0000000003, 'Basic Membership', 'Basic Membership. The most basic membership type we offer.', 1, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Track`
--
-- Creation: Oct 05, 2011 at 03:07 PM
--

DROP TABLE IF EXISTS `Track`;
CREATE TABLE IF NOT EXISTS "Track" (
  "TrackID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'TrackID',
  "TrackNumber" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'TrackNumber',
  "AlbumID" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'AlbumID',
  "TrackName" varchar(64) NOT NULL DEFAULT 'Default Track Name' COMMENT 'TrackName',
  "FilePath" varchar(255) NOT NULL DEFAULT '/' COMMENT 'FilePath',
  "Price" decimal(10,2) unsigned zerofill NOT NULL DEFAULT '00000000.00' COMMENT 'Price',
  "UploadedOn" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'UploadedOn',
  PRIMARY KEY ("TrackID"),
  KEY "TrackNumber" ("TrackNumber"),
  KEY "AlbumID" ("AlbumID")
) AUTO_INCREMENT=3 ;

--
-- Dumping data for table `Track`
--

INSERT DELAYED IGNORE INTO `Track` (`TrackID`, `TrackNumber`, `AlbumID`, `TrackName`, `FilePath`, `Price`, `UploadedOn`) VALUES
(0000000001, 0000000001, 0000000001, 'Intro', '/bob/intromp3', '00000000.20', '0000-00-00 00:00:00'),
(0000000002, 0000000002, 0000000001, 'Bob''s Song', '/bob/bobs_song.mp3', '00000000.40', '2011-10-05 15:25:02');

-- --------------------------------------------------------

--
-- Table structure for table `Transaction`
--
-- Creation: Oct 05, 2011 at 03:07 PM
--

DROP TABLE IF EXISTS `Transaction`;
CREATE TABLE IF NOT EXISTS "Transaction" (
  "TransactionID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'TransactionID',
  "CustomerID" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'CustomerID',
  "PaymentRef" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'PaymentRef',
  "TransactionDate" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'TransactionDate',
  PRIMARY KEY ("TransactionID"),
  KEY "CustomerID" ("CustomerID")
) AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Transaction`
--

INSERT DELAYED IGNORE INTO `Transaction` (`TransactionID`, `CustomerID`, `PaymentRef`, `TransactionDate`) VALUES
(0000000001, 0000000001, 0000000001, '0000-00-00 00:00:00'),
(0000000002, 0000000002, 0000000002, '2011-10-05 15:26:35');

-- --------------------------------------------------------

--
-- Table structure for table `TransactionLine`
--
-- Creation: Oct 05, 2011 at 03:28 PM
--

DROP TABLE IF EXISTS `TransactionLine`;
CREATE TABLE IF NOT EXISTS "TransactionLine" (
  "TransactionID" int(10) unsigned zerofill NOT NULL,
  "TransactionLineID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  "TrackID" int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY ("TransactionLineID"),
  KEY "TrackID" ("TrackID"),
  KEY "TransactionID" ("TransactionID")
) AUTO_INCREMENT=6 ;

--
-- Dumping data for table `TransactionLine`
--

INSERT DELAYED IGNORE INTO `TransactionLine` (`TransactionID`, `TransactionLineID`, `TrackID`) VALUES
(0000000002, 0000000003, 0000000002),
(0000000001, 0000000004, 0000000002),
(0000000001, 0000000005, 0000000001);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Album`
--
ALTER TABLE `Album`
  ADD CONSTRAINT "AlbumGenre" FOREIGN KEY ("AlbumGenre") REFERENCES "Genre" ("GenreID"),
  ADD CONSTRAINT "BandID" FOREIGN KEY ("BandID") REFERENCES "Band" ("BandID");

--
-- Constraints for table `Band`
--
ALTER TABLE `Band`
  ADD CONSTRAINT "BandManager" FOREIGN KEY ("BandManager") REFERENCES "Customer" ("CustomerID"),
  ADD CONSTRAINT "BandGenre" FOREIGN KEY ("BandGenre") REFERENCES "Genre" ("GenreID");

--
-- Constraints for table `Customer`
--
ALTER TABLE `Customer`
  ADD CONSTRAINT "MembershipType" FOREIGN KEY ("MembershipType") REFERENCES "MembershipType" ("MembershipTypeID");

--
-- Constraints for table `Track`
--
ALTER TABLE `Track`
  ADD CONSTRAINT "AlbumID" FOREIGN KEY ("AlbumID") REFERENCES "Album" ("AlbumID");

--
-- Constraints for table `Transaction`
--
ALTER TABLE `Transaction`
  ADD CONSTRAINT "CustomerID" FOREIGN KEY ("CustomerID") REFERENCES "Customer" ("CustomerID");

--
-- Constraints for table `TransactionLine`
--
ALTER TABLE `TransactionLine`
  ADD CONSTRAINT "TrackID" FOREIGN KEY ("TrackID") REFERENCES "Track" ("TrackID"),
  ADD CONSTRAINT "TransactionID" FOREIGN KEY ("TransactionID") REFERENCES "Transaction" ("TransactionID");
SET FOREIGN_KEY_CHECKS=1;
COMMIT;
