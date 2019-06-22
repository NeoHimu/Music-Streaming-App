<?php

// DB table to use
$table = 'bhajans';

// Table's primary key
$primaryKey = 'Location';

// Array of database columns which should be read and sent back to DataTables.
// The `db` parameter represents the column name in the database, while the `dt`
// parameter represents the DataTables column identifier. In this case simple
// indexes
$columns = array(
    array( 'db' => 'Bhajan', 'dt' => 0 ),
    array( 'db' => 'Artist',  'dt' => 1 ),
    array( 'db' => 'Album',   'dt' => 2 ),
    array( 'db' => 'Year', 'dt' => 3 ),
    array( 'db' => 'Size', 'dt' => 4 ),
    array( 'db' => 'Downloads', 'dt' => 5 ),
    array( 'db' => 'Location', 'dt' => 6 )
  );


// SQL server connection information
$sql_details = array(
    'user' => 'root',
    'pass' => 'ssdbhajanadmin',
    'db'   => 'ssdbhajan',
    'host' => 'localhost'
);


require( 'ssp.class.php' );

echo json_encode(
    SSP::simple( $_GET, $sql_details, $table, $primaryKey, $columns )
);

?>
