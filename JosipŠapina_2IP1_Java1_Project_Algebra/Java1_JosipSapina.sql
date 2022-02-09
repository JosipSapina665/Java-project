CREATE DATABASE JosipSapina
go

USE JosipSapina
go



-- User --
create proc GetUser
	@username nvarchar(50),
	@password nvarchar(200)
as
select * from Users where username = @username and pass = @password
go

CREATE PROC GetUsers
AS
    BEGIN
    select * from Users
    END
GO

create proc CreateUser 
	@username nvarchar(50),
	@password nvarchar(200)
as
	insert into Users (username, pass, roleId) values (@username, @password, 2)
go

create proc UpdateUser 
	@username nvarchar(50),
	@password nvarchar(200)
as
BEGIN
    update Users set username = @username, pass = @password, roleId= 1
    where username = @username
END
go

CREATE PROC DeleteUser
    @username nvarchar(50)
AS
    BEGIN
    DELETE from Users WHERE username = @username
    end
GO




-- Movie --

create proc SaveMovie
    @title NVARCHAR(200),
    @pubDate NVARCHAR(200),
    @descr text,
    @duration int,
    @genre NVARCHAR(50),
    @imagePath NVARCHAR(200),
    @id int OUTPUT
AS
    INSERT INTO Movie (title, pubDate, descr, duration, genre, imagePath)
    VALUES (@title, @pubDate, @descr, @duration, @genre, @imagePath)
    set @id = scope_identity()
go

    create proc GetMovie
        @IdMovie int
AS
    BEGIN
    SELECT * from Movie
    WHERE IdMovie = @IdMovie
    end 
go

CREATE PROC GetMovies
AS
    BEGIN
    select * from Movie
    END
GO

CREATE PROC UpdateMovie
    @title NVARCHAR(200),
    @pubDate NVARCHAR(200),
    @descr text,
    @duration int,
    @genre NVARCHAR(50),
    @imagePath NVARCHAR(200),
    @Id int
AS
    BEGIN
    UPDATE Movie SET
    title = @title,
    pubDate = @pubDate,
    descr = @descr,
    duration = @duration,
    genre = @genre,
    imagePath = @imagePath
    WHERE IdMovie = @Id
    END
go

CREATE PROC DeleteMovie
    @IdMovie int
AS
    BEGIN
    DELETE from MovieActor WHERE MovieId = @IdMovie
    delete from MovieDirector where MovieId = @IdMovie
    DELETE from Movie WHERE IdMovie = @IdMovie
    end
GO



    -- Actor --

CREATE PROC SaveActor
    @fullName nvarchar(200),
    @Id int output
AS
    BEGIN
    INSERT into Actor
    VALUES(@fullName)
    set @Id = scope_identity()
    end
go

CREATE proc GetActor
    @IdActor int
AS
    BEGIN
    select * from Actor
    WHERE IdActor = @IdActor
    END
GO

CREATE PROC GetActors
AS
    select * from Actor
GO

CREATE proc UpdateActor
    @fullName NVARCHAR(200),
    @IdActor INT
AS
    BEGIN
    update Actor SET
    fullName = @fullName
    where IdActor = @IdActor
    end
GO

CREATE PROC DeleteActor
    @IdActor int
AS
    BEGIN
    delete from MovieActor where ActorId = @IdActor
    delete from Actor
    WHERE IdActor = @IdActor
    END
GO



    -- Director --

CREATE proc SaveDirector
    @fullName NVARCHAR(200),
    @IdDirector int OUTPUT
AS
    IF NOT EXISTS(select * from Director where fullName = @fullName)
    BEGIN
    insert into Director
    values(@fullName)
    set @IdDirector = scope_identity()
    END
GO

CREATE PROC GetDirector
    @IdDirector INT
AS
    BEGIN
    select * from Director
    where IdDirector = @IdDirector
    END
GO

CREATE PROC GetDirectors
AS
    BEGIN
    select * from Director
    END
GO

CREATE PROC UpdateDirector
    @fullName NVARCHAR(200),
    @IdDirector INT
AS
    BEGIN
    UPDATE Director SET
    fullName = @fullName
    WHERE IdDirector = @IdDirector
    END
GO

create proc DeleteDirector
    @IdDirector INT
AS
    BEGIN
    delete from MovieDirector where DirectorId = @IdDirector
    delete from Director
    where IdDirector = @IdDirector
    END
GO



-- MovieActor --

CREATE PROC SaveMovieActor
    @IdActor int,
    @IdMovie int,
    @Id int output
AS
    BEGIN
    insert into MovieActor
    values (@IdActor, @IdMovie)
     set @Id = scope_identity();
    end
GO

create PROC GetMovieActor
    @IdMovie int
AS
    BEGIN
    select a.* from MovieActor as ma
    INNER JOIN Actor as a on a.IdActor = ma.ActorId
    WHERE ma.MovieId = @IdMovie
    end
GO

CREATE PROC DeleteMovieActor
    @IdActor int,
    @IdMovie INT
AS
    BEGIN
    DELETE from MovieActor
    WHERE ActorId = @IdActor and MovieId = @IdMovie
    END
GO



-- MovieDirector --

CREATE proc SaveMovieDirector
    @IdDirector int,
    @IdMovie int,
    @id int output
AS
    BEGIN
    INSERT into MovieDirector
    VALUES(@IdDirector, @IdMovie)
    set @Id = scope_identity();
    END
GO

create PROC GetMovieDirector
    @IdMovie INT
AS
    BEGIN
    select d.* from MovieDirector as md
    INNER JOIN Director as d on d.IdDirector = md.DirectorId
    WHERE MovieId = @IdMovie
    END
GO

CREATE PROC DeleteMovieDirector
    @IdDirector int,
    @IdMovie INT
AS
    BEGIN
    delete from MovieDirector
    WHERE DirectorId = @IdDirector and MovieId = @IdMovie
    END
GO



--delete all tables

CREATE proc DeleteAll
AS
    BEGIN
    DELETE FROM MovieActor
    DBCC CHECKIDENT('MovieActor', RESEED, 0);
    DELETE FROM MovieDirector
    DBCC CHECKIDENT('MovieActor', RESEED, 0);
    DELETE FROM Movie
    DBCC CHECKIDENT('Movie', RESEED, 0);
    DELETE FROM Director
    DBCC CHECKIDENT('Director', RESEED, 0);
    DELETE FROM Actor
    DBCC CHECKIDENT('Actor', RESEED, 0);
    END
go




-- Tables --

CREATE TABLE Movie(
    IdMovie int not null PRIMARY KEY IDENTITY(1, 1),
    title NVARCHAR(200),
    pubDate nvarchar (200),
    descr text,
    duration int,
    genre nvarchar(50),
    imagePath NVARCHAR(200)
)
GO

CREATE TABLE Users(
    IdUser int PRIMARY key IDENTITY(1, 1),
    username nvarchar(50)not null UNIQUE,
    pass NVARCHAR(50) not null,
    roleID int not null
)
GO

CREATE TABLE Director(
    IdDirector int not null PRIMARY key IDENTITY(1, 1),
    fullName nvarchar(50)
)
GO

CREATE TABLE Actor(
    IdActor int not null PRIMARY KEY IDENTITY(1, 1),
    fullName nvarchar(50)
)
GO

CREATE TABLE MovieActor(
    ActorId int not null FOREIGN key REFERENCES Actor(IdActor) ON DELETE CASCADE,
    MovieId int not null FOREIGN key REFERENCES Movie(IdMovie) ON DELETE CASCADE
    
    CONSTRAINT PK_Movie_Actor PRIMARY key (ActorId, MovieId)
)
go

CREATE TABLE MovieDirector(
    DirectorId int not null FOREIGN key REFERENCES Director(IdDirector),
    MovieId int not null FOREIGN key REFERENCES Movie(IdMovie)
 
    CONSTRAINT PK_Movie_Director PRIMARY key (DirectorId, MovieId) 
)
go



