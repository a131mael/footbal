/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
Core JavaScript functionality for the application.  Performs the required
Restful calls, validates return values, and populates the member table.
 */

/* Builds the updated table for the member list */
function buildMemberRows(contries) {
    return _.template( $( "#member-tmpl" ).html(), {"contries": contries});
}

/* Uses JAX-RS GET to retrieve current member list */
function updateMemberTable() {
    // Display the loader widget
    $.mobile.loading("show");

    $.ajax({
        url: "rest/countries",
        cache: false,
        success: function(data) {
            $( "#members" ).empty().append(buildMemberRows(data));
            $( "#member-table" ).table( "refresh" );
        },
        error: function(error) {
            // console.log("error updating table -" + error.status);
        },
        complete: function() {
            // Hide the loader widget
            $.mobile.loading("hide");
        }
    });
}

/* Uses JAX-RS GET to retrieve current member list */
function updateCountryTable() {
    // Display the loader widget
    $.mobile.loading("show");

    $.ajax({
        url: "rest/countries",
        cache: false,
        success: function(data) {
            $( "#members" ).empty().append(buildMemberRows(data));
            $( "#member-table" ).table( "refresh" );
        },
        error: function(error) {
            // console.log("error updating table -" + error.status);
        },
        complete: function() {
            // Hide the loader widget
            $.mobile.loading("hide");
        }
    });
}

/*
 * Attempts to register a new member using a JAX-RS POST. The callbacks the
 * refresh the member table, or process JAX-RS response codes to update the
 * validation errors.
 */
function registerMember(countryData) {
    // clear existing msgs
    $('span.invalid').remove();
    $('span.success').remove();

    // Display the loader widget
    $.mobile.loading("show");

    $.ajax({
        url: 'rest/countries',
        contentType: "application/json",
        dataType: "json",
        type: "POST",
        data: JSON.stringify(countryData),
        success: function(data) {
            // console.log("Member registered");

            // clear input fields
            $('#reg')[0].reset();

            // mark success on the registration form
            $('#formMsgs').append($('<span class="success">Member Registered</span>'));

            updateMemberTable();
        },
        error: function(error) {
            if ((error.status == 409) || (error.status == 400)) {
                // console.log("Validation error registering user!");

                var errorMsg = $.parseJSON(error.responseText);

                $.each(errorMsg, function(index, val) {
                    $('<span class="invalid">' + val + '</span>').insertAfter($('#' + index));
                });
            } else {
                // console.log("error - unknown server issue");
                $('#formMsgs').append($('<span class="invalid">Unknown server error</span>'));
            }
        },
        complete: function() {
            // Hide the loader widget
            $.mobile.loading("hide");
        }
    });
}


function Listar(){ 
	$("#tblListar").html(""); 
	$("#tblListar").html( "<thead>"
			+ "	<tr>"
			+ "	<th></th>"+ "	<th>Código</th>"
			+ "	<th>Nome</th>"+ "	<th>Telefone</th>"
			+ "	<th>Email</th>"+ "	</tr>"+ "</thead>"+ "<tbody>"+ "</tbody>" ); 
	for(var i in tbClientes){
		var cli = JSON.parse(tbClientes[i]); 
		$("#tblListar tbody").append("<tr>"); 
		$("#tblListar tbody").append("<td><img src='edit.png' alt='"+i+"' class='btnEditar'/><img src='delete.png' alt='"
				+i+"' class='btnExcluir'/></td>");
		$("#tblListar tbody").append("<td>"+cli.Codigo+"</td>");
		$("#tblListar tbody").append("<td>"+cli.Nome+"</td>");
		$("#tblListar tbody").append("<td>"+cli.Telefone+"</td>"); 
		$("#tblListar tbody").append("<td>"+cli.Email+"</td>"); 
		$("#tblListar tbody").append("</tr>"); } 
}

