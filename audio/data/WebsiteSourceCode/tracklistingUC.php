<?php
include("./database/db_connection.php");
require_once 'Mobile_Detect.php';
$detect = new Mobile_Detect;
if( $detect->is('UCBrowser')){
echo "<script>alert('For better experience with the music player on this page,Please use some standard browser');</script>";
}
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Track Listing
    </title>
    
     <meta property="og:url"           content="http://sachosatrambhajan.in/tracklisting.php" />
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="Bhajans" />
  <meta property="og:description"   content="Listen or Download the Bhajans from the huge collection of more than 1000
Bhajans present on the website.
Share it among your friends and family to make them aware of this facility and use it to the
fullest." />
  <meta property="og:image"         content="http://sachosatrambhajan.in/images/tracklisting.png" />
    
    <?php include("libraries.php");?>
    <script>
      $('.dropdown').hover(function(){
        $('.dropdown-toggle', this).trigger('click');
      }
                          );
      <!-- Alphabetic Search Start -->
        (function(){
        // Search function
        $.fn.dataTable.Api.register( 'alphabetSearch()', function ( searchTerm ) {
          this.iterator( 'table', function ( context ) {
            context.alphabetSearch = searchTerm;
          }
                       );
          return this;
        }
                                   );
        // Recalculate the alphabet display for updated data
        $.fn.dataTable.Api.register( 'alphabetSearch.recalc()', function ( searchTerm ) {
          this.iterator( 'table', function ( context ) {
            draw(
              new $.fn.dataTable.Api( context ),
              $('div.alphabet', this.table().container())
            );
          }
                       );
          return this;
        }
                                   );
        // Search plug-in
        $.fn.dataTable.ext.search.push( function ( context, searchData ) {
          // Ensure that there is a search applied to this table before running it
          if ( ! context.alphabetSearch ) {
            return true;
          }
          if ( searchData[0].charAt(0) === context.alphabetSearch ) {
            return true;
          }
          return false;
        }
                                      );
        // Private support methods
        function bin ( data ) {
          var letter, bins = {
          };
          for ( var i=0, ien=data.length ; i<ien ; i++ ) {
            letter = data[i].charAt(0).toUpperCase();
            if ( bins[letter] ) {
              bins[letter]++;
            }
            else {
              bins[letter] = 1;
            }
          }
          return bins;
        }
        function draw ( table, alphabet )
        {
          alphabet.empty();
          alphabet.append( 'Search: ' );
          var columnData = table.column(0).data();
          var bins = bin( columnData );
          $('<span class="clear active"/>')
            .data( 'letter', '' )
            .data( 'match-count', columnData.length )
            .html( 'None' )
            .appendTo( alphabet );
          for ( var i=0 ; i<26 ; i++ ) {
            var letter = String.fromCharCode( 65 + i );
            $('<span/>')
              .data( 'letter', letter )
              .data( 'match-count', bins[letter] || 0 )
              .addClass( ! bins[letter] ? 'empty' : '' )
              .html( letter )
              .appendTo( alphabet );
          }
          $('<div class="alphabetInfo"></div>')
            .appendTo( alphabet );
        }
        $.fn.dataTable.AlphabetSearch = function ( context ) {
          var table = new $.fn.dataTable.Api( context );
          var alphabet = $('<div class="alphabet"/>');
          draw( table, alphabet );
          // Trigger a search
          alphabet.on( 'click', 'span', function () {
            alphabet.find( '.active' ).removeClass( 'active' );
            $(this).addClass( 'active' );
            table
              .alphabetSearch( $(this).data('letter') )
              .draw();
          }
                     );
          // Mouse events to show helper information
          alphabet
            .on( 'mouseenter', 'span', function () {
            alphabet
              .find('div.alphabetInfo')
              .css( {
              opacity: 1,
              left: $(this).position().left,
              width: $(this).width()
            }
                  )
              .html( $(this).data('match-count') )
          }
               )
            .on( 'mouseleave', 'span', function () {
            alphabet
              .find('div.alphabetInfo')
              .css('opacity', 0);
          }
               );
          // API method to get the alphabet container node
          this.node = function () {
            return alphabet;
          };
        };
        $.fn.DataTable.AlphabetSearch = $.fn.dataTable.AlphabetSearch;
        // Register a search plug-in
        $.fn.dataTable.ext.feature.push( {
          fnInit: function ( settings ) {
            var search = new $.fn.dataTable.AlphabetSearch( settings );
            return search.node();
          }
          ,
          cFeature: 'A'
        }
                                       );
      }
         ());
      $(document).ready(function() {
        var table = $('#example').DataTable( {
          dom: 'Alfrtip',
          "order": [[ 3, "desc" ]]
        }
                                           );
      }
                       );
      <!-- Alphabetic Search End -->
    </script>
    <script>
      (function(i,s,o,g,r,a,m){
        i['GoogleAnalyticsObject']=r;
        i[r]=i[r]||function(){
          (i[r].q=i[r].q||[]).push(arguments)}
          ,i[r].l=1*new Date();
        a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];
        a.async=1;
        a.src=g;
        m.parentNode.insertBefore(a,m)
      }
      )(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
      ga('create', 'UA-101190645-1', 'auto');
      ga('send', 'pageview');
    </script>
  </head>
  <body>
  
  <div id="fb-root"></div>
  <script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.9";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
	
    <?php include("navigation.php");?>
    <div class=container-fluid >
      <div class="panel" id=albumPanel>
        <div class="panel-heading" style=" font-size: 12px; color: white">
          <a href="index.php">
            <i class="glyphicon glyphicon-home">
            </i>
          </a>
          <i class="glyphicon glyphicon-menu-right">
          </i>
          Bhajans
          <i class="glyphicon glyphicon-menu-right">
          </i>
          Track Listing
           <i class="glyphicon glyphicon-menu-right">
          </i>
           <!-- Your share button code -->
  <div class="fb-share-button" 
    data-href="http://sachosatrambhajan.in/tracklisting.php" 
    data-layout="button_count" style="float:right">
  </div>
        </div>
        <div class="panel-body">
          <div class=row>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" style="border:8px solid #303030">
              <table id="example" class="display cell-border" cellspacing="0" width="100%" style="color:#9d9d9d;">
                <thead>
                  <tr>
                    <th>Bhajan
                    </th>
                     <th>Artist
                    </th>
                    <th>Album
                    </th>
                    <th>Year
                    </th>
                    <th>Size
                    </th>
                    <th class="no-sort">Play
                    </th>
                    <th class="no-sort">
                      <i class='glyphicon glyphicon-download-alt'>
                    </th>
                    <th>Total
                      <i class='glyphicon glyphicon-download-alt'>
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <?php
include("database/db_connection.php");
$fetchBhajans = mysqli_query($conn,"SELECT * FROM bhajans ORDER BY 'Year' DESC");
while( $row = mysqli_fetch_array( $fetchBhajans ) ){
$Bhajan=$row['Bhajan'];
$Artist=$row['Artist'];
$Album=$row['Album'];
$Year=$row['Year'];
$Size=$row['Size'];
$Location=$row['Location'];
$TotalDownloads=$row['Downloads'];
echo
"<tr>
<td>$Bhajan</td>
<td>$Artist</td>
<td >$Album</td>
<td >$Year</td>
<td >$Size</td>
<td><audio id='audio' class='audio'><source src='$Location' type='audio/mpeg' /></audio>
<a  id='stop' class='stop' style='display:none;' title='Stop Background Music'>
<i class='glyphicon glyphicon-stop' >
</i>
</a>
<a  id='play' class='play' title='Play Background Music'>
<i class='glyphicon glyphicon-play' >
</i>
</a>
</td>
<td><a href='downloads.php?location=$Location&&bhajan=$Bhajan'<i class='glyphicon glyphicon-download-alt'></a></td>
<td >$TotalDownloads</td>
</tr>\n";
}
?>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <?php include("footer.php");?>
  </body>
</html>
<script>
  $("a#play").click(function() {
	  var audios = document.getElementsByTagName('audio');
	    for(var i = 0, len = audios.length; i < len;i++){	        
	            audios[i].pause();
	            audios[i].currentTime=0;
	    }
	    $('.stop').hide();	
		$('.play').show();	
    $(this).closest('td').find('audio').get(0).play();
    $(this).closest('td').find('#play').get(0).style.display='none';
    $(this).closest('td').find('#stop').get(0).style.display='block';
  });
  
  $("a#stop").click(function() {
    var aud=$(this).closest('td').find('audio').get(0);
    aud.pause();
    aud.currentTime = 0;
    $(this).closest('td').find('#play').get(0).style.display='block';
    $(this).closest('td').find('#stop').get(0).style.display='none';
  }
                   );
</script>
