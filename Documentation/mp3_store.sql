-- phpMyAdmin SQL Dump
-- version 3.3.2deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 09, 2011 at 04:28 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.2-1ubuntu4.9

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
-- Table structure for table `AdminLogin`
--
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:23 PM
--

DROP TABLE IF EXISTS `AdminLogin`;
CREATE TABLE IF NOT EXISTS "AdminLogin" (
  "Username" varchar(255) NOT NULL COMMENT 'Username',
  "Password" varchar(255) NOT NULL COMMENT 'Password',
  PRIMARY KEY ("Username")
);

--
-- Dumping data for table `AdminLogin`
--

INSERT DELAYED IGNORE INTO `AdminLogin` (`Username`, `Password`) VALUES
('admin', 'password');

-- --------------------------------------------------------

--
-- Table structure for table `Album`
--
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:22 PM
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
  PRIMARY KEY ("AlbumID")
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `Album`
--


-- --------------------------------------------------------

--
-- Table structure for table `Band`
--
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:22 PM
--

DROP TABLE IF EXISTS `Band`;
CREATE TABLE IF NOT EXISTS "Band" (
  "BandID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'BandID',
  "BandManager" varchar(255) NOT NULL COMMENT 'BandManager',
  "BandName" varchar(64) NOT NULL DEFAULT 'Default Band Name' COMMENT 'BandName',
  "BandDesc" varchar(255) NOT NULL DEFAULT 'No Band Description' COMMENT 'BandDesc',
  "BandGenre" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'BandGenre',
  PRIMARY KEY ("BandID")
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `Band`
--


-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:23 PM
--

DROP TABLE IF EXISTS `Customer`;
CREATE TABLE IF NOT EXISTS "Customer" (
  "Username" varchar(255) NOT NULL COMMENT 'Username',
  "CustomerForename" varchar(25) NOT NULL DEFAULT 'John' COMMENT 'CustomerForename',
  "CustomerSurname" varchar(25) NOT NULL DEFAULT 'Smith' COMMENT 'CustomerSurname',
  "CustomerTitle" varchar(4) NOT NULL DEFAULT 'Mr.' COMMENT 'CustomerTitle',
  "CustomerEmail" varchar(255) NOT NULL DEFAULT 'email@test.com' COMMENT 'CustomerEmail',
  "CustomerAddress" varchar(255) NOT NULL DEFAULT 'Enter an Address' COMMENT 'CustomerAddress',
  "CustomerSince" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'CustomerSince',
  "Verified" tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT 'Verified',
  "MembershipType" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'MembershipType',
  "Password" varchar(128) NOT NULL DEFAULT 'password' COMMENT 'Password',
  PRIMARY KEY ("Username")
);

--
-- Dumping data for table `Customer`
--

INSERT DELAYED IGNORE INTO `Customer` (`Username`, `CustomerForename`, `CustomerSurname`, `CustomerTitle`, `CustomerEmail`, `CustomerAddress`, `CustomerSince`, `Verified`, `MembershipType`, `Password`) VALUES
('customer', 'testy', 'test', 'mr', 'test@test.com', '1 test street, testville', '2011-10-09 16:23:59', 0, 0000000000, 'testpassword');

-- --------------------------------------------------------

--
-- Table structure for table `Genre`
--
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:27 PM
--

DROP TABLE IF EXISTS `Genre`;
CREATE TABLE IF NOT EXISTS "Genre" (
  "GenreID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'GenreID',
  "GenreName" varchar(25) NOT NULL DEFAULT 'Default Genre' COMMENT 'GenreName',
  "GenreDesc" varchar(255) NOT NULL DEFAULT 'No genre description' COMMENT 'GenreDesc',
  PRIMARY KEY ("GenreID")
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
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:27 PM
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
  PRIMARY KEY ("MembershipTypeID")
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
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:22 PM
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
  PRIMARY KEY ("TrackID")
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `Track`
--


-- --------------------------------------------------------

--
-- Table structure for table `Transaction`
--
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:22 PM
--

DROP TABLE IF EXISTS `Transaction`;
CREATE TABLE IF NOT EXISTS "Transaction" (
  "TransactionID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'TransactionID',
  "Username" varchar(255) NOT NULL COMMENT 'Username',
  "PaymentRef" int(10) unsigned zerofill NOT NULL DEFAULT '0000000000' COMMENT 'PaymentRef',
  "TransactionDate" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'TransactionDate',
  PRIMARY KEY ("TransactionID")
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `Transaction`
--


-- --------------------------------------------------------

--
-- Table structure for table `TransactionLine`
--
-- Creation: Oct 09, 2011 at 04:22 PM
-- Last update: Oct 09, 2011 at 04:22 PM
--

DROP TABLE IF EXISTS `TransactionLine`;
CREATE TABLE IF NOT EXISTS "TransactionLine" (
  "TransactionID" int(10) unsigned zerofill NOT NULL,
  "TransactionLineID" int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  "TrackID" int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY ("TransactionLineID")
) AUTO_INCREMENT=1 ;

--
-- Dumping data for table `TransactionLine`
--

SET FOREIGN_KEY_CHECKS=1;
COMMIT;
