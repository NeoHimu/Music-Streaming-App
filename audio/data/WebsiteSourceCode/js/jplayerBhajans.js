/**
 * Created by 23rd and Walnut for Codebasehero.com
 * www.23andwalnut.com
 * www.codebasehero.com
 * User: Saleem El-Amin
 * Date: 6/11/11
 * Time: 6:41 AM
 *
 * Version: 1.01
 * License: MIT License
 */

(function($) {
    $.fn.ttwMusicPlayer = function(playlist, userOptions) {
        var $self = this, defaultOptions, options, cssSelector, appMgr, playlistMgr, interfaceMgr, ratingsMgr, playlist,
                layout, ratings, myPlaylist, current;

        cssSelector = {
            jPlayer: "#jquery_jplayer",
            jPlayerInterface: '.jp-interface',
            playerPrevious: ".jp-interface .jp-previous",
            playerNext: ".jp-interface .jp-next",
            trackList:'.tracklist',
            tracks:'.tracks',
            track:'.track',
            trackRating:'.rating-bar',
            trackInfo:'.track-info',
            rating:'.rating',
            ratingLevel:'.rating-level',
            ratingLevelOn:'.on',
            title: '.title',
            title1:'.title1',
            duration: '.duration',
            buy:'.buy',
            buy1:'.buy1',
            buyNotActive:'.not-active',
            buy1NotActive:'.not-active1',
            playing:'.playing',
            moreButton:'.more',
            player:'.player',
            artist:'.artist',
            artistOuter:'.artist-outer',
            albumCover:'.img',
            description:'.description',
            descriptionShowing:'.showing'
        };

        defaultOptions = {
            ratingCallback:null,
          currencySymbol:'',
            buyText:'',
            buy1Text:'',
            tracksToShow:5,
            autoPlay:false,
            jPlayer:{}
        };

        options = $.extend(true, {}, defaultOptions, userOptions);

        myPlaylist = playlist;

        current = 0;

        appMgr = function() {
            playlist = new playlistMgr();
            layout = new interfaceMgr();

            layout.buildInterface();
            playlist.init(options.jPlayer);

            //don't initialize the ratings until the playlist has been built, which wont happen until after the jPlayer ready event
            $self.bind('mbPlaylistLoaded', function() {
                $self.bind('mbInterfaceBuilt', function() {
                    ratings = new ratingsMgr();
                });
                layout.init();

            });
        };

        playlistMgr = function() {

            var playing = false, markup, $myJplayer = {},$tracks,showHeight = 0,remainingHeight = 0,$tracksWrapper, $more;

            markup = {
                listItem:'<li class="track">' +
                            '<span class="title"></span>' +
                            "<a href='' class='buy1'></a>" +                       
                            "<a href='' class='buy' style=''></a>" +
                            '<span class="duration"></span>' +
                            '<style>a, a:hover, a:active, a:visited, a:focus{text-decoration: none;}</style>'+
                        '</li>'

            };

            function init(playlistOptions) {

                $myJplayer = $('.ttw-music-player .jPlayer-container');


                var jPlayerDefaults, jPlayerOptions;

                jPlayerDefaults = {
                    swfPath: "jquery-jplayer",
                    supplied: "mp3, oga",
                    cssSelectorAncestor:  cssSelector.jPlayerInterface,
                    errorAlerts: false,
                    warningAlerts: false
                };

                //apply any user defined jPlayer options
                jPlayerOptions = $.extend(true, {}, jPlayerDefaults, playlistOptions);

                $myJplayer.bind($.jPlayer.event.ready, function() {

                    //Bind jPlayer events. Do not want to pass in options object to prevent them from being overridden by the user
                    $myJplayer.bind($.jPlayer.event.ended, function(event) {
                        playlistNext();
                    });

                    $myJplayer.bind($.jPlayer.event.play, function(event) {
                        $myJplayer.jPlayer("pauseOthers");
                        $tracks.eq(current).addClass(attr(cssSelector.playing)).siblings().removeClass(attr(cssSelector.playing));
                    });

                    $myJplayer.bind($.jPlayer.event.playing, function(event) {
                        playing = true;
                    });

                    $myJplayer.bind($.jPlayer.event.pause, function(event) {
                        playing = false;
                    });

                    //Bind next/prev click events
                    $(cssSelector.playerPrevious).click(function() {
                        playlistPrev();
                        $(this).blur();
                        return false;
                    });

                    $(cssSelector.playerNext).click(function() {
                        playlistNext();
                        $(this).blur();
                        return false;
                    });

                    $self.bind('mbInitPlaylistAdvance', function(e) {
                        var changeTo = this.getData('mbInitPlaylistAdvance');

                        if (changeTo != current) {
                            current = changeTo;
                            playlistAdvance(current);
                        }
                        else {
                            if (!$myJplayer.data('jPlayer').status.srcSet) {
                                playlistAdvance(0);
                            }
                            else {
                                togglePlay();
                            }
                        }
                    });

                    buildPlaylist();
                    //If the user doesn't want to wait for widget loads, start playlist now
                    $self.trigger('mbPlaylistLoaded');

                    playlistInit(options.autoplay);
                });

                //Initialize jPlayer
                $myJplayer.jPlayer(jPlayerOptions);
            }

            function playlistInit(autoplay) {
                current = 0;

                if (autoplay) {
                    playlistAdvance(current);
                }
                else {
                    playlistConfig(current);
                    $self.trigger('mbPlaylistInit');
                }
            }

            function playlistConfig(index) {
                current = index;
                $myJplayer.jPlayer("setMedia", myPlaylist[current]);
            }

            function playlistAdvance(index) {
                playlistConfig(index);

                if (index >= options.tracksToShow)
                    showMore();

                $self.trigger('mbPlaylistAdvance');
                $myJplayer.jPlayer("play");
            }

            function playlistNext() {
                var index = (current + 1 < myPlaylist.length) ? current + 1 : 0;
                playlistAdvance(index);
            }

            function playlistPrev() {
                var index = (current - 1 >= 0) ? current - 1 : myPlaylist.length - 1;
                playlistAdvance(index);
            }

            function togglePlay() {
                if (!playing)
                    $myJplayer.jPlayer("play");
                else $myJplayer.jPlayer("pause");
            }

            function buildPlaylist() {
                var $ratings = $();

                $tracksWrapper = $self.find(cssSelector.tracks);

                //set up the html for the track ratings
                for (var i = 0; i < 10; i++)
                    $ratings = $ratings.add(markup.ratingBar);

                for (var j = 0; j < myPlaylist.length; j++) {
                    var $track = $(markup.listItem);

                    //since $ratings refers to a specific object, if we just use .html($ratings) we would be moving the $rating object from one list item to the next
                    $track.find(cssSelector.rating).html($ratings.clone());

                    $track.find(cssSelector.title).html(trackName(j));
                    
                    $track.find(cssSelector.title1).html(trackName(j));

                    $track.find(cssSelector.duration).html(duration(j));

                    setRating('track', $track, j);

                    setBuyLink($track, j);
                    
                    setBuy1Link($track, j);

                    $track.data('index', j);

                    $tracksWrapper.append($track);
                }

                $tracks = $(cssSelector.track);

                $tracks.slice(0, options.tracksToShow).each(function() {
                    showHeight += $(this).outerHeight();
                });

                $tracks.slice(options.tracksToShow, myPlaylist.length).each(function() {
                    remainingHeight += $(this).outerHeight();
                });

                if (remainingHeight > 0) {
                    var $trackList = $(cssSelector.trackList);

                    $tracksWrapper.height(showHeight);
                    $trackList.addClass('show-more-button');

                    $trackList.find(cssSelector.moreButton).click(function() {
                        $more = $(this);

                        showMore();
                    });
                }

                $tracks.find('.title').click(function() {
                    playlistAdvance($(this).parents('li').data('index'));
                });
                
                $tracks.find('.title1').click(function(event) {   
                	event.preventDefault();
                	if (!playing)
                		{
                		 playlistAdvance($(this).parents('li').data('index'));
                		 $myJplayer.jPlayer("play");
                		 $(this).parents('li').find('#play').hide();
                         $(this).parents('li').find('#stop').show();
                		}
                	else
                		{
                		$myJplayer.jPlayer("pause");
                		playlistAdvance($(this).parents('li').data('index'));
                		$('.title2').hide();
                		$('.title1').show();
                		$(this).parents('li').find('#play').hide();
                        $(this).parents('li').find('#stop').show();
                		}
                });
                
                
                $tracks.find('.title2').click(function() {
                	 togglePlay();
                     $(this).parents('li').find('#play').show();
                     $(this).parents('li').find('#stop').hide();
                	
                });
            }

            function showMore() {
                if (isUndefined($more))
                    $more = $self.find(cssSelector.moreButton);

                $tracksWrapper.animate({height: showHeight + remainingHeight}, function() {
                    $more.animate({opacity:0}, function() {
                        $more.slideUp(function() {
                            $more.parents(cssSelector.trackList).removeClass('show-more-button');
                            $more.remove();

                        });
                    });
                });
            }

            function duration(index) {
                return !isUndefined(myPlaylist[index].duration) ? myPlaylist[index].duration : '-';
            }

            function setBuyLink($track, index) {
                if (!isUndefined(myPlaylist[index].buy)) {
                    $track.find(cssSelector.buy).removeClass(attr(cssSelector.buyNotActive)).attr('href', myPlaylist[index].buy).html(buyText(index));
                }
            }
            
            function setBuy1Link($track, index) {
                if (!isUndefined(myPlaylist[index].buy1)) {
                    $track.find(cssSelector.buy1).removeClass(attr(cssSelector.buy1NotActive)).attr('href', myPlaylist[index].buy1).html(buy1Text(index));
                }
            }

            function buyText(index) {
                return (!isUndefined(myPlaylist[index].price) ? options.currencySymbol + myPlaylist[index].price : '') + ' ' + options.buyText;
            }
            
            function buy1Text(index) {
                return (!isUndefined(myPlaylist[index].price1) ? options.currencySymbol + myPlaylist[index].price1 : '') + ' ' + options.buy1Text;
            }

            return{
                init:init,
                playlistInit:playlistInit,
                playlistAdvance:playlistAdvance,
                playlistNext:playlistNext,
                playlistPrev:playlistPrev,
                togglePlay:togglePlay,
                $myJplayer:$myJplayer
            };

        };

        ratingsMgr = function() {

            var $tracks = $self.find(cssSelector.track);

            function bindEvents() {

                //Handler for when user hovers over a rating
                $(cssSelector.rating).find(cssSelector.ratingLevel).hover(function() {
                    $(this).addClass('hover').prevAll().addClass('hover').end().nextAll().removeClass('hover');
                });

                //Restores previous rating when user is finished hovering (assuming there is no new rating)
                $(cssSelector.rating).mouseleave(function() {
                    $(this).find(cssSelector.ratingLevel).removeClass('hover');
                });

                //Set the new rating when the user clicks
                $(cssSelector.ratingLevel).click(function() {
                    var $this = $(this), rating = $this.parent().children().index($this) + 1, index;

                    if ($this.hasClass(attr(cssSelector.trackRating))) {
                        rating = rating / 2;
                        index = $this.parents('li').data('index');

                        if (index == current)
                            applyCurrentlyPlayingRating(rating);
                    }
                    else {
                        index = current;
                        applyTrackRating($tracks.eq(index), rating);
                    }


                    $this.prevAll().add($this).addClass(attr(cssSelector.ratingLevelOn)).end().end().nextAll().removeClass(attr(cssSelector.ratingLevelOn));

                    processRating(index, rating);
                });
            }

            function processRating(index, rating) {
                myPlaylist[index].rating = rating;
                runCallback(options.ratingCallback, index, myPlaylist[index], rating);
            }

            bindEvents();
        };

        interfaceMgr = function() {

            var $player, $title, $artist, $albumCover;


            function init() {
                $player = $(cssSelector.player),
                        $title = $player.find(cssSelector.title),
                        $artist = $player.find(cssSelector.artist),
                        $albumCover = $player.find(cssSelector.albumCover);

                setDescription();

                $self.bind('mbPlaylistAdvance mbPlaylistInit', function() {
                    setTitle();
                    setArtist();
                    setRating('current', null, current);
                    setCover();
                });
            }

            function buildInterface() {
                var markup, $interface;

                //I would normally use the templating plugin for something like this, but I wanted to keep this plugin's footprint as small as possible
                markup ='<div class="ttw-music-player">' +
                '<div class="player jp-interface">' +
                '        <div class="player-controls">' +
                '            <div class="main">' +
                '                <div class="previous jp-previous"></div>' +
                '                <div class="play jp-play"></div>' +
                '                <div class="pause jp-pause"></div>' +
                '                <div class="next jp-next"></div>' +
                '<!-- These controls aren\'t used by this plugin, but jPlayer seems to require that they exist -->' +
                '                <span class="unused-controls">' +
                '                    <span class="jp-video-play"></span>' +
                '                    <span class="jp-stop"></span>' +
                '                    <span class="jp-mute"></span>' +
                '                    <span class="jp-unmute"></span>' +
                '                    <span class="jp-volume-bar"></span>' +
                '                    <span class="jp-volume-bar-value"></span>' +
                '                    <span class="jp-volume-max"></span>' +
                '                    <span class="jp-current-time"></span>' +
                '                    <span class="jp-duration"></span>' +
                '                    <span class="jp-full-screen"></span>' +
                '                    <span class="jp-restore-screen"></span>' +
                '                    <span class="jp-repeat"></span>' +
                '                    <span class="jp-repeat-off"></span>' +
                '                    <span class="jp-gui"></span>' +
                '                </span>' +
                '            </div>' +
                '            <div class="progress-wrapper">' +
                '                <div class="progress jp-seek-bar">' +
                '                    <div class="elapsed jp-play-bar"></div>' +
                '                </div>' +
                '            </div>' +
						'	<div class="duration-time">  '+         
							  ' <span class="jp-current-time">00:00:00</span> '+           
							   ' <span class="jp-duration">00:00:00</span>   '+             
						   ' </div>'+
                '        </div>' +
                '    </div>' +
                
                '<div class="tracklistTitle">'+
                '<span class="bhajanTitle">Bhajans</span>'+
                '<span class="playTitle">Play</span>'+
                '<span class="downloadTitle">Download</span>'+
                '<span class="downloadTitleMobile"><i class="glyphicon glyphicon-download-alt"></i></span>'+
                '<span class="totalDownloadsTitle">Total Downloads</span>'+
                '<span class="totalDownloadsTitleMobile">Total <i class="glyphicon glyphicon-download-alt"></i></span>'+
                '</div>'+
                
                
                '    <div class="tracklist">' +
                '        <ol class="tracks"> </ol>' +
                '        <div class="more">View More...</div>' +
                '    </div>' +
                '    <div class="jPlayer-container"></div>' +
                '</div>';



                $interface = $(markup).css({display:'none', opacity:0}).appendTo($self).slideDown('slow', function() {
                    $interface.animate({opacity:1});

                    $self.trigger('mbInterfaceBuilt');
                });
            }

            function setTitle() {
                $title.html(trackName(current));
            }

            function setArtist() {
                if (isUndefined(myPlaylist[current].artist))
                    $artist.parent(cssSelector.artistOuter).animate({opacity:0}, 'fast');
                else {
                    $artist.html(myPlaylist[current].artist).parent(cssSelector.artistOuter).animate({opacity:1}, 'fast');
                }
            }

            function setCover() {
                $albumCover.animate({opacity:0}, 'fast', function() {
                    if (!isUndefined(myPlaylist[current].cover)) {
                        var now = current;
                        $('<img src="' + myPlaylist[current].cover + '" alt="album cover" />', this).imagesLoaded(function(){
                            if(now == current)
                                $albumCover.html($(this)).animate({opacity:1})
                        });
                    }
                });
            }

            function setDescription() {
                if (!isUndefined(options.description))
                    $self.find(cssSelector.description).html(options.description).addClass(attr(cssSelector.descriptionShowing)).slideDown();
            }

            return{
                buildInterface:buildInterface,
                init:init
            }

        };

        /** Common Functions **/
        function trackName(index) {
            if (!isUndefined(myPlaylist[index].title))
                return myPlaylist[index].title;
            else if (!isUndefined(myPlaylist[index].mp3))
                return fileName(myPlaylist[index].mp3);
            else if (!isUndefined(myPlaylist[index].oga))
                return fileName(myPlaylist[index].oga);
            else return '';
        }

        function fileName(path) {
            path = path.split('/');
            return path[path.length - 1];
        }

        function setRating(type, $track, index) {
            if (type == 'track') {
                if (!isUndefined(myPlaylist[index].rating)) {
                    applyTrackRating($track, myPlaylist[index].rating);
                }
            }
            else {
                //if the rating isn't set, use 0
                var rating = !isUndefined(myPlaylist[index].rating) ? Math.ceil(myPlaylist[index].rating) : 0;
                applyCurrentlyPlayingRating(rating);
            }
        }

        function applyCurrentlyPlayingRating(rating) {
            //reset the rating to 0, then set the rating defined above
            $self.find(cssSelector.trackInfo).find(cssSelector.ratingLevel).removeClass(attr(cssSelector.ratingLevelOn)).slice(0, rating).addClass(attr(cssSelector.ratingLevelOn));

        }

        function applyTrackRating($track, rating) {
            //multiply rating by 2 since the list ratings have 10 levels rather than 5
            $track.find(cssSelector.ratingLevel).removeClass(attr(cssSelector.ratingLevelOn)).slice(0, rating * 2).addClass(attr(cssSelector.ratingLevelOn));

        }


        /** Utility Functions **/
        function attr(selector) {
            return selector.substr(1);
        }

        function runCallback(callback) {
            var functionArgs = Array.prototype.slice.call(arguments, 1);

            if ($.isFunction(callback)) {
                callback.apply(this, functionArgs);
            }
        }

        function isUndefined(value) {
            return typeof value == 'undefined';
        }

        appMgr();
    };
})(jQuery);

(function($) {
// $('img.photo',this).imagesLoaded(myFunction)
// execute a callback when all images have loaded.
// needed because .load() doesn't work on cached images

// mit license. paul irish. 2010.
// webkit fix from Oren Solomianik. thx!

// callback function is passed the last image to load
//   as an argument, and the collection as `this`


    $.fn.imagesLoaded = function(callback) {
        var elems = this.filter('img'),
                len = elems.length;

        elems.bind('load',
                function() {
                    if (--len <= 0) {
                        callback.call(elems, this);
                    }
                }).each(function() {
            // cached images don't fire load sometimes, so we reset src.
            if (this.complete || this.complete === undefined) {
                var src = this.src;
                // webkit hack from http://groups.google.com/group/jquery-dev/browse_thread/thread/eee6ab7b2da50e1f
                // data uri bypasses webkit log warning (thx doug jones)
                this.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                this.src = src;
            }
        });

        return this;
    };
})(jQuery);